package com.micromachines.customerservice.service.repositories.couch;

import java.util.List;

import org.ektorp.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micromachines.customerservice.service.Customer;
import com.micromachines.customerservice.service.CustomerService;
import com.micromachines.customerservice.service.repositories.Repository;

@Component
public class CouchCustomerService implements CustomerService {
	
	@Autowired
	private Repository repository;
	
	@Override
	public Customer getCustomer(String tenant, String id) {
		return repository.forCustomer(tenant).get(id);
	}
	
	@Override
	public Customer createCustomer(String tenant, Customer customer) {
		repository.forCustomer(tenant).add(customer);
		return customer;
	}
	
	@Override
	public List<Customer> getCustomers(String tenant) {
		return repository.forCustomer(tenant).getAll();
	}
	
	@Override
	public List<Customer> findCustomersByLastName(String tenant, String lastName) {
		return repository.forCustomer(tenant).findByLastName(lastName);
	}
	
	
	@Override
	public Page<Customer> getPaginatedCustomers(String tenant, String key) {
		return repository.forCustomer(tenant).getPaginatedCustomers(key);
	}

}
