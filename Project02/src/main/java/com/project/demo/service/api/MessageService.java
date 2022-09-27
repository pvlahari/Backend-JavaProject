package com.project.demo.service.api;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public interface MessageService {

	String getString(final String bundleKey, final Object... objects);

    String getFieldErrorMessage(FieldError fieldError);

    String getGlobalErrorMessage(ObjectError globalError);

    <E extends Enum<E>> String getEnumString(E e);
}
