package com.project.demo.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.project.demo.service.api.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageSource messageSource;

	@Override
	public String getString(String bundleKey, Object... objects) {
		try {
			return messageSource.getMessage(bundleKey, objects, Locale.US);
		} catch (final NoSuchMessageException e) {
			return bundleKey;
		}
	}

	@Override
	public String getFieldErrorMessage(final FieldError fieldError) {
		try {
			return messageSource.getMessage(fieldError, Locale.US);
		} catch (final NoSuchMessageException e) {
			return fieldError.getField();
		}
	}

	@Override
	public String getGlobalErrorMessage(final ObjectError globalError) {
		try {
			return messageSource.getMessage(globalError, Locale.US);
		} catch (final NoSuchMessageException e) {
			return globalError.getCode();
		}
	}

	@Override
	public <E extends Enum<E>> String getEnumString(final E c) {
		return getString(c.getClass().getSimpleName() + "." + c.name());
	}

}
