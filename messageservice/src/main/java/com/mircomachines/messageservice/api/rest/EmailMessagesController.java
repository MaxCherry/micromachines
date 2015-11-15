package com.mircomachines.messageservice.api.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mircomachines.messageservice.domain.EmailMessage;
import com.mircomachines.messageservice.service.EmailMessageService;


@RestController
@RequestMapping("{tenant}/emails")
public class EmailMessagesController {
	
	private final static Logger LOG = LoggerFactory.getLogger(EmailMessagesController.class);
	
	@Autowired
	private EmailMessageService emailMessageService;
	
	@RequestMapping(name="/", method=RequestMethod.GET)
    public String index() {
        return "Welcome to Customer Management Service @ micromachines";
    }
	
	
	@RequestMapping(name="/", method=RequestMethod.POST)
	public void sendEmail(@PathVariable String tenant, @RequestBody @Valid EmailMessage message, @RequestParam(required=false, defaultValue = "0") long ttl) {
		LOG.info("Entering EmailMessagesController#sendEmail. {}", message);
		if(ttl > 0) {
			emailMessageService.sendEmail(tenant, message, ttl);
		} else {
			emailMessageService.sendEmail(tenant, message);
		}
		

	}

}
