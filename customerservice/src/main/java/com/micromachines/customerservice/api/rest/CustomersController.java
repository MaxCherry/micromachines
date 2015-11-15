package com.micromachines.customerservice.api.rest;


import java.util.List;

import javax.validation.Valid;

import org.ektorp.DocumentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.micromachines.customerservice.api.rest.pagination.PaginatedCustomers;
import com.micromachines.customerservice.service.Customer;
import com.micromachines.customerservice.service.CustomerService;
import com.micromachines.customerservice.service.Views;
import com.micromachines.customerservice.service.commands.Commands;
import com.micromachines.customerservice.service.commands.CreateCustomerCommand;

@RestController
@RequestMapping("{tenant}/customers")
public class CustomersController {
	
	private final static Logger LOG = LoggerFactory.getLogger(CustomersController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RabbitTemplate template;
	
	@RequestMapping("/")
    public String index() {
        return "Welcome to Customer Management Service @ micromachines";
    }
	
	@JsonView(Views.Public.class)
	@RequestMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable String tenant, @PathVariable String id) {
		return customerService.getCustomer(tenant, id);
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/customers/")
	public PaginatedCustomers getCustomers(@PathVariable String tenant, @RequestParam(required=false) String key) {
		return new PaginatedCustomers(customerService.getPaginatedCustomers(tenant, key));
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/customers/",params={"lastName"})
	public List<Customer> getFilteredCustomers(@PathVariable String tenant, @RequestParam String lastName) {
		return customerService.findCustomersByLastName(tenant, lastName);
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/customers/", method=RequestMethod.POST)
	public Customer addCustomer(@PathVariable String tenant, @RequestBody @Valid Customer customer) {
		LOG.debug("Entering CustomersController#addCustomer. {}", customer);
		return customerService.createCustomer(tenant, customer);
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(value="/customers/", method=RequestMethod.POST, params={"rpc"})
	public ResponseEntity<?> addCustomerViaQueue(@PathVariable String tenant, @RequestBody @Valid Customer customer) {
		CreateCustomerCommand command = Commands.createCustomer(tenant, customer);
		Customer createdCustomer = (Customer) template.convertSendAndReceive("microservices-exchange", command.key(), command);
		
		if(createdCustomer == null) {
			LOG.info("Create customer operation accepted but not yet finished");
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(createdCustomer, HttpStatus.OK);
		
	}
		
	@ExceptionHandler(DocumentNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Document not found")
	public void notFound(DocumentNotFoundException ex) {
		LOG.info("Requested element not found", ex);
	}
	
	

}
