package com.mircomachines.messageservice.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class EmailAddress {
	
	@Email
	@NotNull
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
