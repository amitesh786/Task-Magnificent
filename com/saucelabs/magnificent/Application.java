package com.saucelabs.magnificent;

import com.saucelabs.magnificent.thread.DaemonThread;
public class Application {
    
	public static void main(String[] args) {
		DaemonThread.getInstance().start();
	}
}
