package com.saucelabs.magnificent.thread;

import com.saucelabs.magnificent.config.Config;
public class MonitorGauge {

	private static MonitorGauge instance;
	Boolean[] health;
	int downs;
	int index = 0;	
	boolean appDown;
	
	public static MonitorGauge getInstance(){
		if(instance == null) {
			instance = new MonitorGauge();
		}
		return instance;
	}
    
    private MonitorGauge(){
        initOrResetHealth();
    }
    
    private void initOrResetHealth(){
        long durations = (Config.MONITOR_DURATION * 60 * 1000) / Config.SLEEP_DURATION;
        health = new Boolean[(int) durations];
        index = 0;
    }
    
    private void logDown() {
        if(appDown) {
            return;
        }
        downs ++;
        if(downs > Config.DOWN_FAILURES) {
            appDown = true;
            log("Application down");
            initOrResetHealth();
        }
    }
    
    private void updateHealth() {
        int success = 0, failure = 0;
        for(Boolean b : health) {
            if(b == null) {
                continue;
            }
            if(b) {
                success ++;
            } else {
                failure ++;
            }
        }
        double health = (success * 100) / (success + failure);
        log("Health: " + (int) health + " %");
    }
    
    private void log(String message) {
        System.out.println(message);
    }
    
    private void logHealth(boolean b) {
        downs = 0; // reset downs
        appDown = false;
        if(index >= health.length) {
            index = 0;
        }		
        health[index] = b;		
        index ++;		
        updateHealth();		
    }
	
	public void captureNewResponse(int code){
		if(code == -1) {
			logDown();
		} else if(code >= 200 && code < 400){
			logHealth(true);
		} else{
			logHealth(false);
		}
	}
}
