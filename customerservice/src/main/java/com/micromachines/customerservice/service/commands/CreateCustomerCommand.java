package com.micromachines.customerservice.service.commands;

import com.micromachines.customerservice.service.Customer;


public class CreateCustomerCommand extends CustomerCommand {
	
	private static final long serialVersionUID = 1L;

	private Customer customer;
	
	public CreateCustomerCommand(String tenant) {
		super(tenant);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
	
	

}
