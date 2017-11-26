package com.saucelabs.magnificent.exception;

public class ApplicationException extends Exception {
    
	private static final long serialVersionUID = 42L;
	private ExceptionMessage exceptionMessage;

	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}
    
    public ApplicationException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
