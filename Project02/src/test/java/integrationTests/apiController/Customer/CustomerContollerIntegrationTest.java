package integrationTests.apiController.Customer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.project.demo.dto.CustomerApiDto;

import integrationTests.apiController.BaseApiControllerIntegrationTest;

public class CustomerContollerIntegrationTest extends BaseApiControllerIntegrationTest {

	private static final String ENDPOINT_PREFIX = "/customers";
	private static final String REGISTER_ENDPOINT = "/register";
	private static final String ALL_ENDPOINT = "/all";
	private static final String DELETE_ENDPOINT = "/delete";

	private static final String SEPARATOR = "/";

	// data

	private static final int CUSTOMER_ID = 3;
	private static final String CUSTOMER_FIRST_NAME = "kishore";
	private static final String CUSTOMER_LAST_NAME = "kota";
	private static final String CUSTOMER_EMAIL = "kishore.kota@smaato.com";
	private static final String CUSTOMER_CITY = "Banglore";
	private static final int CUSTOMER_PINCODE = 123456;
	private static final String CUSTOMER_ROLE = "user";

	@Test
	public void getAllCustomersInvalidTest() throws Exception {

		final String endPoint = BASE_ENDPOINT + ENDPOINT_PREFIX + ALL_ENDPOINT;

		final CustomerApiDto expectedDto = getDefaultCustomerApiDto(CUSTOMER_ID, CUSTOMER_FIRST_NAME, CUSTOMER_EMAIL,
				CUSTOMER_CITY, CUSTOMER_PINCODE, CUSTOMER_ROLE);
		
		GetRequestWithValidationErrors(expectedDto, endPoint, MockMvcResultMatchers.status().isNotFound(), HttpStatus.NOT_FOUND, "firstName");

	}

	private void GetRequestWithValidationErrors(final CustomerApiDto customerApiDto, final String endPoint,
			final ResultMatcher status, final HttpStatus httpStatus, final String... fieldNames) throws Exception {

		final String expectedDto = doGetAndCheckResult(endPoint, status);
		checkErrorResponseWithValidationErrors(expectedDto, httpStatus, fieldNames);
	}

	private String doGetAndCheckResult(final String endPoint, final ResultMatcher status) throws Exception {

		final String contentBody = getMockMvc()
				.perform(MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON)).andExpect(status)
				.andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_CHARSET_UTF_8)).andReturn()
				.getResponse().getContentAsString();
		
		System.out.println("test Result" + ' ' + "=>" + ' ' + contentBody);

		Assert.assertTrue(StringUtils.isNotEmpty(contentBody));

		return contentBody;
	}

	private CustomerApiDto getDefaultCustomerApiDto(final int customerId, final String firstName, final String email,
			final String city, final int pinCode, final String role) {

		final CustomerApiDto customerDto = new CustomerApiDto();

		customerDto.setId(customerId);
		customerDto.setFirstName(firstName);
		customerDto.setEmail(email);
		customerDto.setCity(city);
		customerDto.setPinCode(pinCode);
		customerDto.setRole(role);

		return customerDto;
	}

}
