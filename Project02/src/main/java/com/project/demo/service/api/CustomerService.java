package com.project.demo.service.api;

import java.util.List;

import com.project.demo.dto.CustomerApiDto;

public interface CustomerService {

     List <CustomerApiDto> getAllCustomers();
     
     CustomerApiDto registerCustomer(CustomerApiDto customerApiDto);
     
     CustomerApiDto getCustomerInfo(int customerId);
    
     CustomerApiDto updateCustomer(CustomerApiDto customerApiDto, int customerId);
     
     void deleteCustomer(int customerId);

}

