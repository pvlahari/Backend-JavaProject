package com.project.demo.apicontroller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.project.demo.dto.CustomerApiDto;
import com.project.demo.validators.BaseValidator;

@Component
public class CustomerApiDtoValidator extends BaseValidator {

    final String NOT_NULL = "NotNull";
    final String FIRST_NAME = "firstName";
    final String ROLE = "role";

	@Override
	public boolean supports(Class<?> clazz) {
        return CustomerApiDtoValidationWrapper.class.equals(clazz);
	}
	
	// mandatory fields - firstName, email, city, pinCode, role

	@Override
	protected void concreteValidate(Object target, Errors errors) {
		
		final CustomerApiDtoValidationWrapper customerApiDtoValidationWrapper = (CustomerApiDtoValidationWrapper) target;
		final CustomerApiDto dto = customerApiDtoValidationWrapper.getDto();
		
//		if (null == dto.getFirstName() || dto.getFirstName().isEmpty()) {
//            errors.rejectValue(FIRST_NAME, NOT_NULL, convertArgs(dto.getFirstName()), null);
//			throw new CustomerValidationException(FIRST_NAME);
//		}
	}

}
