package integrationTests.apiController;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.validators.ErrorMsgDto;
import com.project.demo.validators.ValidationErrorDto;

public class BaseApiControllerIntegrationTest {

	protected static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
	protected static final String BASE_ENDPOINT = "/api";

	private final ObjectMapper mapper = new ObjectMapper();
	private MockMvc mockMvc;

	protected void checkErrorResponseWithValidationErrors(final String contentBody, final HttpStatus httpStatus,
			final String... fieldNames) throws Exception {

		checkErrorResponseWithValidationErrors(contentBody, null, httpStatus, fieldNames);
	}

	protected void checkErrorResponseWithValidationErrors(final String contentBody, final String validationMessage,
			final HttpStatus httpStatus, final String... fieldNames) throws Exception {

		Assert.assertTrue(StringUtils.isNotEmpty(contentBody));
		final ErrorMsgDto errorMessageDto = getMapper().readValue(contentBody, ErrorMsgDto.class);
		Assert.assertNotNull(errorMessageDto);

		if (fieldNames == null || fieldNames.length == 0) {
			Assert.assertNull(errorMessageDto.getValidationErrors());
		} else {
			Assert.assertNotNull(errorMessageDto.getValidationErrors());

			if (validationMessage != null) {
				final ValidationErrorDto validation = errorMessageDto.getValidationErrors().get(0);
				Assert.assertEquals(validationMessage, validation.getMessage());
			}

			final List<String> fieldNameList = getValidationErrorFieldNames(errorMessageDto.getValidationErrors());
			System.out.println("#### fieldNameList: " + fieldNameList);
			Assert.assertEquals(fieldNames.length, fieldNameList.size());

			for (final String fieldName : fieldNames) {
				System.out.println("#### fieldName: " + fieldName);
				Assert.assertTrue(fieldNameList.contains(fieldName));
			}
		}

		Assert.assertEquals(buildStatusMessage(httpStatus), errorMessageDto.getStatus());
		Assert.assertNull(errorMessageDto.getGlobalMessage());
	}

	protected List<String> getValidationErrorFieldNames(final List<ValidationErrorDto> validationErrors) {
		final List<String> fieldNames = new ArrayList<>();
		if (validationErrors == null) {
			return fieldNames;
		}
		for (final ValidationErrorDto validationErrorDto : validationErrors) {
			fieldNames.add(validationErrorDto.getField());
		}
		return fieldNames;
	}

	protected String buildStatusMessage(final HttpStatus status) {
		return status.value() + " (" + status.getReasonPhrase() + ")";
	}

	protected ObjectMapper getMapper() {
		return mapper;
	}

	protected MockMvc getMockMvc() {
		return mockMvc;
	}

}
