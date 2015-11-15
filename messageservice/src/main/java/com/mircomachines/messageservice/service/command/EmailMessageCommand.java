package com.mircomachines.messageservice.service.command;

import com.mircomachines.Command;
import com.mircomachines.messageservice.domain.EmailMessage;

public class EmailMessageCommand extends Command {
	
	private static final long serialVersionUID = 1L;

	private EmailMessage emailMessage;

	public EmailMessageCommand(String tenant, EmailMessage emailMessage) {
		super(tenant);
		this.emailMessage = emailMessage;
	}
	
	public EmailMessage getEmailMessage() {
		return emailMessage;
	}
	
	@Override
	public String key() {
		return "cmd.message.send." + getTenant();
	}

}
