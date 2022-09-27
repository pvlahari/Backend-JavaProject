package com.project.demo.validators;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiControllerValidationException extends BaseRuntimeException {

	private static final long serialVersionUID = 1L;

	private final BindingResult bindingResult;

	public ApiControllerValidationException(final BindingResult result) {
		super();
		bindingResult = result;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

}
