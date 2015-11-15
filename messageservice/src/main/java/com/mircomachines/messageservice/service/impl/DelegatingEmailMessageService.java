package com.mircomachines.messageservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mircomachines.messageservice.domain.EmailMessage;
import com.mircomachines.messageservice.service.EmailMessageService;
import com.mircomachines.messageservice.service.command.Commands;
import com.mircomachines.messageservice.service.command.EmailMessageCommand;

@Component
public class DelegatingEmailMessageService implements EmailMessageService {
	
	private final static Logger LOG = LoggerFactory.getLogger(DelegatingEmailMessageService.class);
	
	@Autowired
	private RabbitTemplate template;

	@Override
	public void sendEmail(String tenant, EmailMessage message) {	
		EmailMessageCommand command = Commands.createEmailMessageCommand(tenant, message);
		LOG.info("Enqueing command [{}]", command);
		template.convertAndSend("microservices-exchange", command.key(), command);
	}

	@Override
	public void sendEmail(String tenant, EmailMessage message, long ttl) {
		throw new RuntimeException("Not yet supported");
		
	}
	
	

}
