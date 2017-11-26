package com.saucelabs.magnificent.thread;

import com.saucelabs.magnificent.exceptions.RuntimeExceptionApp;
import com.saucelabs.magnificent.exceptions.MsgException;
import com.saucelabs.magnificent.exceptions.ExceptionApp;

import com.saucelabs.magnificent.https.HTTPConnector;
import com.saucelabs.magnificent.config.Config;

public class DaemonConnection {
	private static DaemonConnection instance;
	private DaemonConnection() {}
	
	public void start() {
		HTTPConnector connector = HTTPConnector.getInstance();
		
		while (true) {
			try {
				Thread.sleep(Config.SLEEP_DURATION);
			} catch (InterruptedException e) {
				throw new RuntimeExceptionApp(MsgException.THREAD_SLEEP_FAIL);
			}
			try {
				int code = connector.connect();
				MonitorCounter.getInstance().captureNewResponse(code);
			} catch (ExceptionApp e) {
				MonitorCounter.getInstance().captureNewResponse(-1);
				continue;
			}	
		}
	}
    
    public static DaemonConnection getInstance() {
        if(instance == null) {
            instance = new DaemonConnection();
        }
        return instance;
    }
}
