package com.project.demo.validators;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

public class ErrorMsgDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String globalMessage;
	private List<ValidationErrorDto> validationErrors;
	private String status;

	// constructors

	public ErrorMsgDto() {
	}
	

	public ErrorMsgDto(final String globalMessage, final HttpStatus status) {
		this.globalMessage = globalMessage;
		this.status = buildStatusString(status);
	}

	public ErrorMsgDto(final String globalMessage, final HttpStatus status,
			final List<ValidationErrorDto> validationErrors) {
		this.status = buildStatusString(status);
		this.globalMessage = globalMessage;
		this.validationErrors = validationErrors;
	}

	
	private String buildStatusString(final HttpStatus status) {
		return status.toString() + " (" + status.getReasonPhrase() + ")";
	}

	
	// getters & setters
	

	public String getGlobalMessage() {
		return globalMessage;
	}

	public void setGlobalMessage(String globalMessage) {
		this.globalMessage = globalMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ValidationErrorDto> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ValidationErrorDto> validationErrors) {
		this.validationErrors = validationErrors;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
