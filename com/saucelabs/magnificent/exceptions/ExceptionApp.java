package com.saucelabs.magnificent.exceptions;

public class ExceptionApp extends Exception {
    
	private static final long serialVersionUID = 42L;
	private MsgException exceptionMessage;

	public MsgException getExceptionMessage() {
		return exceptionMessage;
	}
    
    public ExceptionApp(MsgException exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
