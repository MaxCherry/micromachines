package com.micromachines.customerservice.service.commands;

import com.micromachines.customerservice.service.Customer;

public class Commands {

	public static CreateCustomerCommand createCustomer(String tenant, Customer customer) {
		return new CreateCustomerCommand(tenant, customer);
	}

}
