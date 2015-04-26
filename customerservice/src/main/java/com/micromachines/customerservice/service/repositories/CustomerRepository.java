package com.micromachines.customerservice.service.repositories;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.Page;
import org.ektorp.PageRequest;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;
import org.springframework.util.StringUtils;

import com.micromachines.customerservice.service.Customer;

public class CustomerRepository extends CouchDbRepositorySupport<Customer>{

	private static final int MAX_RESULT = 20;

	CustomerRepository(CouchDbConnector db) {
		super(Customer.class, db);
		initStandardDesignDocument();
	}

	public CustomerRepository(CouchDbConnector db, boolean createIfNotExists) {
		super(Customer.class, db, createIfNotExists);
		initStandardDesignDocument();
	}

	public CustomerRepository(CouchDbConnector db, String designDocName) {
		super(Customer.class, db, designDocName);
		initStandardDesignDocument();
	}
	
	
	@GenerateView
	public List<Customer> findByLastName(String lastName) {
		return queryView("by_lastName", lastName);
	}
	

	@View( name = "by_creationDate", map = "function(doc) { emit(doc.creationDate, null);}")
	public com.micromachines.customerservice.service.Page<Customer> getPaginatedCustomers(String key) {
		PageRequest pageRequest;
		if(!StringUtils.isEmpty(key)) {
			pageRequest = PageRequest.fromLink(key);
		} else {
			pageRequest = PageRequest.firstPage(MAX_RESULT);
		}
		ViewQuery query = new ViewQuery()
			.designDocId("_design/Customer")
			.viewName("by_creationDate")
			.includeDocs(true);
		
		Page<Customer> customerPage = db.queryForPage(query, pageRequest, Customer.class);
		return new com.micromachines.customerservice.service.Page<Customer>(customerPage);
		
	}
	
	

}
