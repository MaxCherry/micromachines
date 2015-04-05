package com.micromachines.customerservice.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.LRUMap;
import com.micromachines.customerservice.service.repositories.couch.CouchConnector;

@Component
public class Repository {
	
	@Autowired
	private CouchConnector couch;
	
	private static LRUMap<String, CustomerRepository> customerRepositories = new LRUMap<String, CustomerRepository>(100, 1000);
	
	public CustomerRepository forCustomer(String tenant) {
		if(customerRepositories.get(tenant) == null) {
			customerRepositories.put(tenant, new CustomerRepository(couch.getConnector(tenant), true));
		}
		return customerRepositories.get(tenant);
	}
	

}
