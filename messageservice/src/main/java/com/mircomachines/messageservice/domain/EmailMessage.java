package com.mircomachines.messageservice.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.mail.SimpleMailMessage;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class EmailMessage extends AbstractMessage {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	private List<String> to;
	
	private List<String> cc;
	
	private List<String> bcc;
	
	private String from;
	
	private String subject;
	private String body;
	
	@JsonIgnore
	public SimpleMailMessage toMessage() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to.toArray(new String[]{}));
		if(cc != null)
			mailMessage.setCc(cc.toArray(new String[]{}));
		if(bcc != null)
			mailMessage.setBcc(bcc.toArray(new String[]{}));
		
		mailMessage.setFrom(from);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		mailMessage.setSentDate(new Date());
		return mailMessage;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
