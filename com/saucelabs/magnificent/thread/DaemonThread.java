package com.saucelabs.magnificent.thread;

import com.saucelabs.magnificent.exception.ApplicationRuntimeException;
import com.saucelabs.magnificent.exception.ExceptionMessage;
import com.saucelabs.magnificent.http.HTTPConnector;
import com.saucelabs.magnificent.config.Config;
import com.saucelabs.magnificent.exception.ApplicationException;

public class DaemonThread {
	private static DaemonThread instance;
	private DaemonThread() {}
	
	public void start() {
		HTTPConnector connector = HTTPConnector.getInstance();
		
		while (true) {
			try {
				Thread.sleep(Config.SLEEP_DURATION);
			} catch (InterruptedException e) {
				throw new ApplicationRuntimeException(ExceptionMessage.THREAD_SLEEP_FAIL);
			}
			try {
				int code = connector.connect();
				MonitorGauge.getInstance().captureNewResponse(code);
			} catch (ApplicationException e) {
				MonitorGauge.getInstance().captureNewResponse(-1);
				continue;
			}	
		}
	}
    
    public static DaemonThread getInstance() {
        if(instance == null) {
            instance = new DaemonThread();
        }
        return instance;
    }
}
