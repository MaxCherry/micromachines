package com.micromachines.customerservice.service;

import java.util.List;

import org.ektorp.Page;


public interface CustomerService {

	Customer getCustomer(String tenant, String id);

	Customer createCustomer(String tenant, Customer customer);

	List<Customer> getCustomers(String string);

	List<Customer> findCustomersByLastName(String tenant, String name);

	Page<Customer> getPaginatedCustomers(String string, String key);

}
