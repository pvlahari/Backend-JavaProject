package com.project.demo.dao.persistence.api;

import java.util.List;

import com.project.demo.dao.domain.Customer;

public interface CustomerDao {
	
	// basic CRUD
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);

	void registerCustomer(Customer customer);

	void updateCustomerInfo(Customer customer);
	
	void deleteCustomerById(Customer customer);
	
	// others
	
	Boolean checkCustomerExistsByMailId(String email);
	
}
