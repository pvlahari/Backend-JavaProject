package com.project.demo.validators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public abstract class BaseValidator implements Validator {

	@Override
	public void validate(final Object target, final Errors errors) {

		concreteValidate(target, errors);

		if (errors.hasErrors()) {
			System.out.println("BaseValidator" + "=>" + errors);
			throw new ApiControllerValidationException((BindingResult) errors);
		}
	}

	protected abstract void concreteValidate(Object target, Errors errors);

	protected <T> void invokeNestedValidator(final Validator validator, final T entity, final Errors errors,
			final String subPath) {
		if (entity == null) {
			return;
		}

		try {
			errors.pushNestedPath(subPath);
			ValidationUtils.invokeValidator(validator, entity, errors);
		} catch (final ApiControllerValidationException e) {
		} finally {
			errors.popNestedPath();
		}
	}

	protected <T> void invokeBeanValidation(final T entity, final Errors errors, final String subPath) {
		final Set<ConstraintViolation<T>> validationErrors = doBeanValidate(entity);
		errors.pushNestedPath(subPath);
		for (final ConstraintViolation<T> constraintViolation : validationErrors) {
			errors.rejectValue(constraintViolation.getPropertyPath().toString(),
					getErrorCode(constraintViolation.getMessageTemplate()));
		}
		errors.popNestedPath();
	}

	protected <T> Set<ConstraintViolation<T>> doBeanValidate(final T entity) {
		final javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		return validator.validate(entity);
	}
	
    protected Object[] convertArgs(final Object... args) {
        return args;
    }

    protected String getErrorCode(final String messageTemplate) {
        final String[] split = messageTemplate.toString().split("\\.");
        return split.length > 1 ? split[split.length - 2] : "UnknownError";
    }

}
