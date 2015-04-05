package com.micromachines.customerservice.api.rest.pagination;

import org.ektorp.Page;

public abstract class PaginatedResult {
	
	private String keyNext;
	private String keyPrevious;
	
	public PaginatedResult(Page<?> page) {
		if(page.isHasNext()) {
			keyNext = page.getNextLink();
		}
		if(page.isHasPrevious()) {
			keyPrevious = page.getPreviousLink();
		}
		
	}
	
	public String getKeyNext() {
		return keyNext;
	}
	
	public String getKeyPrevious() {
		return keyPrevious;
	}

}
