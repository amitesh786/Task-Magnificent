package com.saucelabs.magnificent.exception;

public class ApplicationRuntimeException extends RuntimeException {
    
	private static final long serialVersionUID = 42L;	
	private ExceptionMessage exceptionMessage;

	public ExceptionMessage getExceptionMessage() {
		return exceptionMessage;
	}
    
    public ApplicationRuntimeException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
