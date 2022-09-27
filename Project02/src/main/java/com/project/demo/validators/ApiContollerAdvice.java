package com.project.demo.validators;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.project.demo.service.api.MessageService;

@ControllerAdvice
public class ApiContollerAdvice {

	public static final String INVALID_DATA_MESSAGE = "Invalid message";
	public static final String INVALID_VALUE_MESSAGE = "Invalid value message";

	@Autowired
	private MessageService messageService;

	// HttpMessageNotReadableException

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ErrorMsgDto handleHttpMessageNotReadableException(final HttpMessageNotReadableException exception) {

		final Throwable cause = exception.getCause();

		final JsonMappingException jme = (JsonMappingException) cause;
		final String path = getPath(jme.getPath());

		final List<ValidationErrorDto> errorList = Arrays
				.asList(new ValidationErrorDto(path, messageService.getString(INVALID_VALUE_MESSAGE, path)));

		return new ErrorMsgDto(messageService.getString(INVALID_DATA_MESSAGE), HttpStatus.BAD_REQUEST, errorList);
	};
	

	// MethodArgumentNotValidException

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorMsgDto handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {

		final List<ValidationErrorDto> validationErrors = exception.getBindingResult().getFieldErrors().stream()
				.map(e -> {
					return convertError(e);
				}).collect(Collectors.toList());

		validationErrors.addAll(exception.getBindingResult().getGlobalErrors().stream().map(e -> {
			return convertError((FieldError) e);
		}).collect(Collectors.toList()));

		return new ErrorMsgDto(messageService.getString(INVALID_DATA_MESSAGE), HttpStatus.BAD_REQUEST,
				validationErrors);
	}
	
	
	// --------------------

	private String getPath(final List<Reference> referenceList) {

		return referenceList.stream().filter(e -> e != null).map(e -> {
			if (StringUtils.isEmpty(e.getFieldName())) {
				return "" + e.getIndex();
			}
			return e.getFieldName();
		}).collect(Collectors.joining("."));
	}
	

	private ValidationErrorDto convertError(final FieldError fieldError) {
		return new ValidationErrorDto(fieldError.getField(), messageService.getFieldErrorMessage(fieldError));
	}


}
