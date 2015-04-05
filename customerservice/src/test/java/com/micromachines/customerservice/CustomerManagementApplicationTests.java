package com.micromachines.customerservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.micromachines.customerservice.CustomerManagementApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CustomerManagementApplication.class)
@WebAppConfiguration
public class CustomerManagementApplicationTests {

	@Test
	public void contextLoads() {
	}

}
