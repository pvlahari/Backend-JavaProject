package com.project.demo.validators;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class BaseRuntimeException extends ContextedRuntimeException  {

	private static final long serialVersionUID = 1L;

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(final Throwable cause) {
		super(cause);
	}

	public BaseRuntimeException(final String message) {
		super(message);
	}

}
