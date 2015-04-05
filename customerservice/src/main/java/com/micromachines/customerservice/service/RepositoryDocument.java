package com.micromachines.customerservice.service;

import java.util.Map;

import org.ektorp.Attachment;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

abstract class RepositoryDocument extends CouchDbDocument {
	
	@JsonView(Views.Internal.class)
	private String type = getClass().getSimpleName();
	
	@JsonProperty("_id")
	public String getId() {
		return super.getId();
	}

	@JsonProperty("_id")
	public void setId(String s) {
		super.setId(s);
	}

	@JsonView(Views.Internal.class)
	@JsonProperty("_rev")
	public String getRevision() {
		return super.getRevision();
	}

	@JsonView(Views.Internal.class)
	@JsonProperty("_rev")
	public void setRevision(String s) {
		super.setRevision(s);
	}
	
	@JsonProperty(ATTACHMENTS_NAME)
	public Map<String, Attachment> getAttachments() {
		return super.getAttachments();
	}
	
	public String getType() {
		return type;
	}
	
	
		
	

	

}
