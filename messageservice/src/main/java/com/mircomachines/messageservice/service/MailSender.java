package com.mircomachines.messageservice.service;

import com.mircomachines.messageservice.domain.EmailMessage;


public interface MailSender {
	
	void sendMail(EmailMessage message);

}
