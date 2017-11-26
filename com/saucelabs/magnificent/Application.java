package com.saucelabs.magnificent;

import com.saucelabs.magnificent.thread.DaemonConnection;
public class Application {
    
	public static void main(String[] args) {
		DaemonConnection.getInstance().start();
	}
}
