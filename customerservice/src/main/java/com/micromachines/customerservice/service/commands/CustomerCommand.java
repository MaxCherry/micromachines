package com.micromachines.customerservice.service.commands;


public abstract class CustomerCommand extends Command {
	
	private static final long serialVersionUID = 1L;
	
	public CustomerCommand(String tenant) {
		super(tenant);
	}


}
