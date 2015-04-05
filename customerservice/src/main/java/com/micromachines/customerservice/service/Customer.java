package com.micromachines.customerservice.service;

import java.util.Date;

import org.ektorp.support.TypeDiscriminator;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

@TypeDiscriminator("doc.type === 'Customer'")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Customer extends RepositoryDocument {
	
	
	private static final long serialVersionUID = 1L;

	private Date creationDate = new Date();
	
	@JsonView(Views.Public.class)
	@NotEmpty(message="customer.firstName required")
	private String firstName;
	
	@NotEmpty(message="customer.lastName required")
	private String lastName;
	
	
	public Customer() {
		
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
