package com.project.demo.validators;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ValidationErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private String message;

	public ValidationErrorDto() {
	}

	public ValidationErrorDto(final String field, final String message) {
		this.field = field;
		this.message = message;
	}
	
	public ValidationErrorDto(final String message) {
		this.message = message;
	}

	// getters & setters

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
