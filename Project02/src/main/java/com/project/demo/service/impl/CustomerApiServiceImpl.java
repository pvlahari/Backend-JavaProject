package com.project.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.dao.domain.Customer;
import com.project.demo.dao.persistence.api.CustomerDao;
import com.project.demo.dto.CustomerApiDto;
import com.project.demo.service.api.CustomerService;
import com.project.demo.service.impl.exception.CustomerAlreadyExistsRuntimeException;
import com.project.demo.service.impl.exception.CustomerNotFoundException;

@Service
public class CustomerApiServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

//	@Autowired
//	CustomerMapper customerMapper;

	@Override
	public List<CustomerApiDto> getAllCustomers() {

		final List<Customer> customers = customerDao.getAllCustomers();

		// final List<CustomerApiDto> result = customers.stream().map(c ->
		// customerMapper.map(c)).collect(Collectors.toList());

		final List<CustomerApiDto> result = customers.stream().map(this::mapperDaoToDto).collect(Collectors.toList());

		return result;
	}

	@Override
	@Transactional
	public CustomerApiDto registerCustomer(CustomerApiDto customerApiDto) {

		// throw error if customer already exists - email

//		final List<Customer> customers = customerDao.getAllCustomers();		

		// one way - not prefered
		
//		for (Customer customer : customers) {
//			
//			if (customer.getEmail().equals(customerApiDto.getEmail())) {
//				
//				throw new CustomerAlreadyExistsRuntimeException("The given mailId already exists" + " " + customerApiDto.getEmail());
//				
//			}
//			
//		}
		
		// other way - prefered way
		
		final Boolean customerExists = customerDao.checkCustomerExistsByMailId(customerApiDto.getEmail());
		
		if (customerExists == true) {
			
			throw new CustomerAlreadyExistsRuntimeException("The given customer already exists" + " " + customerApiDto.getEmail());
			
		}
		
		// save customer after validation

		final Customer newCustomer = new Customer();

		mapperDtoToDao(customerApiDto, newCustomer); // Api to Dao mapper

		customerDao.registerCustomer(newCustomer);

		return mapperDaoToDto(newCustomer); // return newly created customer
	}

	@Override
	@Transactional
	public CustomerApiDto getCustomerInfo(int customerId) {
		
		final Customer customer = customerDao.getCustomerById(customerId);
		
		if (customer == null) {
			
			throw new CustomerNotFoundException("The given customer Id not found" + " " +customerId);
		}

		final CustomerApiDto customerApiDto = mapperDaoToDto(customer); // Dao to Api mapper

		return customerApiDto; // return customer details
	}

	@Override
	@Transactional
	public CustomerApiDto updateCustomer(CustomerApiDto customerApiDto, int customerId) {

		final Customer customer = customerDao.getCustomerById(customerId); 	// get existing customer by Id

		mapperDtoToDao(customerApiDto, customer); 	// update existing customer details

		customerDao.updateCustomerInfo(customer);

		return mapperDaoToDto(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {

		Customer customer = new Customer();

		customer = customerDao.getCustomerById(customerId);

		customer.setDeleted(true);
		customer.setDeletedAt(new Date());

		customerDao.deleteCustomerById(customer);
	}

	// Api <-> Dto, DB <-> Dao => while sending data from db to Api (client) -
	// (mapping should be done from db/Dao to Api/Dto) - outgoing requests

	private CustomerApiDto mapperDaoToDto(Customer customer) {
		CustomerApiDto customerApiDto = new CustomerApiDto();

		customerApiDto.setId(customer.getId());
		customerApiDto.setFirstName(customer.getFirstName());
		customerApiDto.setLastName(customer.getLastName());
		customerApiDto.setEmail(customer.getEmail());
		customerApiDto.setCity(customer.getCity());
		customerApiDto.setPinCode(customer.getPinCode());
		customerApiDto.setRole(customer.getRole());

		return customerApiDto;
	}

	// Api <-> Dto, DB <-> Dao => while sending data from Api (client) to db -
	// (mapping should be done from Api/Dto to db/Dao) - incoming requests

	private void mapperDtoToDao(final CustomerApiDto customerApiDto, final Customer customer) {
		customer.setFirstName(customerApiDto.getFirstName());
		customer.setLastName(customerApiDto.getLastName());
		customer.setEmail(customerApiDto.getEmail());
		customer.setCity(customerApiDto.getCity());
		customer.setPinCode(customerApiDto.getPinCode());
		customer.setRole(customerApiDto.getRole());
	}
}
