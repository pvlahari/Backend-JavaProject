package com.project.demo.apicontroller;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.project.demo.dto.CustomerApiDto;

public class CustomerApiDtoValidationWrapper {

	private final CustomerApiDto dto;

	public CustomerApiDtoValidationWrapper(final CustomerApiDto dto) {
		this.dto = dto;
	}

	public CustomerApiDto getDto() {
		return dto;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
