package com.project.demo.dao.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.demo.dao.domain.Customer;
import com.project.demo.dao.persistence.api.CustomerDao;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private EntityManager entityManager;

	@Autowired
	public CustomerDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	private static final String GET_ALL_CUSTOMERS = "FROM Customer";

	private static final String GET_CUSTOMER_INFO = "SELECT c FROM Customer c WHERE c.id = :customer_id";

	 private static final String GET_CUSTOMER_BY_MAILID = "SELECT c FROM Customer c WHERE c.email = :customer_emailId";

	@Override
	public List<Customer> getAllCustomers() {

		final TypedQuery<Customer> query = entityManager.createQuery(GET_ALL_CUSTOMERS, Customer.class); // create query

		List<Customer> customers = query.getResultList(); // execute query

		return customers; // return result

	}

	@Override
	public void registerCustomer(Customer customer) {

		entityManager.persist(customer);

	}

	@Override
	public void updateCustomerInfo(Customer customer) {

		entityManager.merge(customer);

	}

	@Override
	public Customer getCustomerById(int customerId) {

		final TypedQuery<Customer> query = entityManager.createQuery(GET_CUSTOMER_INFO, Customer.class);

		query.setParameter("customer_id", customerId);
		
		Customer result = null;

		try {

			result = query.getSingleResult();
			
		} catch (Exception e) {
			
			result = null;
		}
		
		return result;

	}
	
	public Boolean checkCustomerExistsByMailId(String customerEmail) {

		final TypedQuery<Customer> query = entityManager.createQuery(GET_CUSTOMER_BY_MAILID, Customer.class);

		query.setParameter("customer_emailId", customerEmail);
		
		Boolean result = null;

		try {
			
			if (query.getSingleResult() != null) {
				
				return result = true;
			}
			
		} catch (Exception e) {
			
			result = false;
		}
		
		return result;
		
	}

	@Override
	public void deleteCustomerById(Customer customer) {

		entityManager.remove(customer);

	}

}
