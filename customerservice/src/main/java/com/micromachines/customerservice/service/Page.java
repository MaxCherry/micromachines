package com.micromachines.customerservice.service;

import java.util.List;

import org.ektorp.PageRequest;

public class Page<T> extends org.ektorp.Page<T> {

	public Page(List<T> rows, int totalSize, int pageSize,
			PageRequest previousPageRequest, PageRequest nextPageRequest) {
		super(rows, totalSize, pageSize, previousPageRequest, nextPageRequest);
	}

	public Page(org.ektorp.Page<T> page) {
		this(page.getRows(), page.getTotalSize(), page.getPageSize(), page.getPreviousPageRequest(), page.getNextPageRequest());
	}
	
	

}
