package com.dbc.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class TradeServiceTest {

	public void testGetTradeFromRestService() throws Exception {
		String result = new RestTemplate()
				.getForObject(
						"http://localhost:8080/search/trade/{reference}",
						String.class, "1");
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void testCreateTradeFromRestService() throws Exception {
		String result = new RestTemplate()
				.getForObject(
						"http://localhost:8080/create/trade/{id}",
						String.class, "1");
		Assert.assertNotNull(result);
		System.out.println(result);
	}
}
