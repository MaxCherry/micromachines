package com.micromachines.customerservice.service.commands;

import com.micromachines.customerservice.service.Customer;


public class CreateCustomerCommand extends CustomerCommand {
	
	private static final long serialVersionUID = 1L;

	private Customer customer;
	
	public CreateCustomerCommand(String tenant) {
		this(tenant, null);
	}

	public CreateCustomerCommand(String tenant, Customer customer) {
		super(tenant);
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String key() {
		return "cmd.customer.create." + getTenant();
	}
	
	
	
	
	
	
	
	

}
