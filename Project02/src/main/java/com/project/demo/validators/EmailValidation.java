package com.project.demo.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

public class EmailValidation extends ErrorMsgDto {

	private static final long serialVersionUID = 1L;

	private String message;

	public EmailValidation(String email) {

		final Boolean emailCheck = validEmailCheck(email);

		if (emailCheck) {
			
			new ErrorMsgDto(this.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	private Boolean validEmailCheck(String email) {

		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {

			return false;
			
		} else {

			this.setMessage("Please provide a valid email address");
			
			return true;
		}

	}

	// getters & setters

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
