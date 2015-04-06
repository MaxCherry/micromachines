package com.micromachines.customerservice.api.rest.pagination;

import java.util.List;

import org.ektorp.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.micromachines.customerservice.service.Customer;

@JsonInclude(Include.NON_NULL)
public class PaginatedCustomers extends PaginatedResult {
	
	private List<Customer> customers;
	
	public PaginatedCustomers(Page<Customer> page) {
		super(page);
		customers = page.getRows();
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	

}
