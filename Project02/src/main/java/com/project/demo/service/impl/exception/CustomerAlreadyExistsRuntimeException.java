package com.project.demo.service.impl.exception;


public class CustomerAlreadyExistsRuntimeException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistsRuntimeException(final String emailId) {
        super(emailId);
    }

	public CustomerAlreadyExistsRuntimeException (String message, Throwable cause) {
		super(message, cause);
	}


}
