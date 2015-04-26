package com.micromachines.customerservice.service.messaging.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.micromachines.customerservice.service.Customer;
import com.micromachines.customerservice.service.CustomerService;
import com.micromachines.customerservice.service.commands.CreateCustomerCommand;

public class CustomerCommandReceiver {
	
	private final static Logger LOG = LoggerFactory.getLogger(CustomerCommandReceiver.class);
	
	@Autowired
	private CustomerService customerService;
	
	public Customer execute(Object command) {
		LOG.info("Received : {}", command);
		if(CreateCustomerCommand.class.isInstance(command)) {
			CreateCustomerCommand customerCommand = (CreateCustomerCommand) command;
			return customerService.createCustomer(customerCommand.getTenant(), customerCommand.getCustomer());
		} else {
			//throw new RuntimeException("Unknown command. Feeding error queue?");
			LOG.error("Command not executable. {}", command);
		}
		return null;
		
		
		
	}
	
	

}
