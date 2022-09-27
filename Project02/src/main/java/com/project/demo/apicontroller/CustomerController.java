package com.project.demo.apicontroller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.CustomerApiDto;
import com.project.demo.service.api.CustomerService;
import com.project.demo.service.api.MessageService;
import com.project.demo.service.impl.exception.CustomerAlreadyExistsRuntimeException;
import com.project.demo.service.impl.exception.CustomerNotFoundException;
import com.project.demo.validators.ErrorMsgDto;
import com.project.demo.validators.ValidationErrorDto;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	private static final String INVALID_DATA = "Invalid Data";

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MessageService messageService;

	// private static List<ValidationErrorDto> errorList = new ArrayList<>();

//	@GetMapping("/check")
//	public String check() {
//		return "welcome";
//	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<CustomerApiDto> getAllCustomers() {

		final List<CustomerApiDto> customersResult = customerService.getAllCustomers();
		return customersResult;
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerApiDto registerCustomer(@RequestBody @Valid CustomerApiDto customerApiDto) {

		// System.out.println("Controller" + "=>" + bindingResult);
		// customerApiDtoValidator.validate(new
		// CustomerApiDtoValidationWrapper(customerApiDto), bindingResult);

		return customerService.registerCustomer(customerApiDto);
	}

	@RequestMapping(path = "/{customerId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public CustomerApiDto getCustomer(@PathVariable final int customerId) {

		final CustomerApiDto customerApiDto = customerService.getCustomerInfo(customerId);

		return customerApiDto;
	}

	@RequestMapping(path = "/{customerId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public CustomerApiDto updateCustomer(@PathVariable final int customerId,
			@RequestBody @Valid final CustomerApiDto customerApiDto) {

		return customerService.updateCustomer(customerApiDto, customerId);
	}

	@RequestMapping(path = "/delete/{customerId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable final int customerId) {

		customerService.deleteCustomer(customerId);
	}

	// exception Handlers

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomerAlreadyExistsRuntimeException.class)
	public ErrorMsgDto handleException(final CustomerAlreadyExistsRuntimeException exception) {

		return new ErrorMsgDto(INVALID_DATA, HttpStatus.BAD_REQUEST,
				Arrays.asList(new ValidationErrorDto(messageService.getString(exception.getMessage()))));

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomerNotFoundException.class)
	public ErrorMsgDto handleException(final CustomerNotFoundException exception) {

		// return new ErrorMsgDto(INVALID_DATA, exception.getMessage(),
		// HttpStatus.BAD_REQUEST);

		return new ErrorMsgDto(INVALID_DATA, HttpStatus.BAD_REQUEST,
				Arrays.asList(new ValidationErrorDto(messageService.getString(exception.getMessage()))));

	}

}
