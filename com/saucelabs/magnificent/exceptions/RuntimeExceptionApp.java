package com.saucelabs.magnificent.exceptions;

public class RuntimeExceptionApp extends RuntimeException {
    
	private static final long serialVersionUID = 42L;	
	private MsgException exceptionMessage;

	public MsgException getExceptionMessage() {
		return exceptionMessage;
	}
    
    public RuntimeExceptionApp(MsgException exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
