package com.micromachines.customerservice.service.repositories.couch;

import javax.annotation.PostConstruct;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.ObjectMapperFactory;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.impl.StdObjectMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micromachines.customerservice.service.Views;


@Component
public class CouchConnector {
	
	
	
	@Value("${couch.username}")
	private String userName;
	
	@Value("${couch.password}")
	private String password;
	
	@Value("${couch.url}")
	private String url;
		
	
	private static CouchDbInstance couchInstance;

	public CouchDbConnector getConnector(String database) {
		return couchInstance.createConnector(database, true);
	}
	
	@PostConstruct
	void init() throws Exception {
		
		HttpClient client = new StdHttpClient.Builder()
			.username(userName)
			.password(password)
			.url(url)
			.build();
		//StdObjectMapperFactory factory = new StdObjectMapperFactory();
		//ObjectMapper objectMapper = new ObjectMapper();
		//factory.setObjectMapper(objectMapper);
		//objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Views.Internal.class));
		couchInstance = new StdCouchDbInstance(client);
	}

}
