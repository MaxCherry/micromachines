package com.mircomachines.messageservice.service;

import com.mircomachines.messageservice.domain.EmailMessage;

public interface EmailMessageService {

	void sendEmail(String tenant, EmailMessage message);

	void sendEmail(String tenant, EmailMessage message, long ttl);

}
