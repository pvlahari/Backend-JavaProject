package com.project.demo.validators;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorMsgDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private String message;
	private String status;

	// constructors

	public ErrorMsgDto() {
	}

	public ErrorMsgDto(String field, String message, String status) {
		this.field = field;
		this.message = message;
		this.status = status;
	}

	public ErrorMsgDto(final String message, final HttpStatus status) {
		this.status = buildStatusString(status);
		this.message = message;
	}
	
    private String buildStatusString(final HttpStatus status) {
        return status.toString() + " (" + status.getReasonPhrase() + ")";
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
