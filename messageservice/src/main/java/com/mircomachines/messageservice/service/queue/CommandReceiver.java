package com.mircomachines.messageservice.service.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.mircomachines.messageservice.service.MailSender;
import com.mircomachines.messageservice.service.command.EmailMessageCommand;

public class CommandReceiver {
	
	private final static Logger LOG = LoggerFactory.getLogger(CommandReceiver.class);
	
	@Autowired
	private RabbitTemplate template;
	
	@Autowired
	private MailSender mailSender;
	
	public void execute(Object command) {
		LOG.info("Received : {}", command);
		if(EmailMessageCommand.class.isInstance(command)) {
			EmailMessageCommand messageCommand = (EmailMessageCommand) command;
			try {
				mailSender.sendMail(messageCommand.getEmailMessage());
			} catch (Exception e) {
				LOG.error("Exception occurred {}, requeing", e.getMessage());
				delayMessage(messageCommand);
			}
		} else {
			//throw new RuntimeException("Unknown command. Feeding error queue?");
			LOG.error("Command not executable. {}", command);
		}
		
	}

	private void delayMessage(EmailMessageCommand command) {
		template.convertAndSend("microservices-delay-exchange", command.key(), command);
		
	}
	
	

}
