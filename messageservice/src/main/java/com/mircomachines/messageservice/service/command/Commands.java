package com.mircomachines.messageservice.service.command;

import com.mircomachines.messageservice.domain.EmailMessage;

public class Commands {

	public static EmailMessageCommand createEmailMessageCommand(String tenant, EmailMessage emailMessage) {
		return new EmailMessageCommand(tenant, emailMessage);
	}

}
