package com.mircomachines.messageservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mircomachines.messageservice.domain.EmailMessage;
import com.mircomachines.messageservice.service.MailSender;

/**
 * Dummy implementation using a (Ralf's) static smtp config.
 * 
 * @author wagner_patrick
 *
 */
@Component
public class StaticSmtpMailSender implements MailSender {
	
	private final static Logger LOG = LoggerFactory.getLogger(StaticSmtpMailSender.class);
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(EmailMessage message) {
		LOG.info("Sending email to: " + message.getTo().get(0));
		mailSender.send(message.toMessage());
	}
	
	

}
