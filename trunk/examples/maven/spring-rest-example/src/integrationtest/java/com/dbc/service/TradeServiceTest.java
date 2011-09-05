package com.dbc.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.dbc.model.Trade;
import com.thoughtworks.xstream.XStream;

public class TradeServiceTest {

	public void testGetTradeFromRestService() throws Exception {
		String result = new RestTemplate()
				.getForObject(
						"http://localhost:8080/search/trade/{reference}",
						String.class, "1");
		
		Trade t = getTradeFromXml(result);
		System.out.println(t.getId());
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void testCreateTradeFromRestService() throws Exception {
		long id = 1L;
		String trade = createTrade(1L);
		Trade t = getTradeFromXml(trade);
		Assert.assertEquals(t.getId(), id);
	}

	private Trade getTradeFromXml(String trade) {
		XStream xstream = new XStream();
		xstream.processAnnotations(Trade.class);
		Trade t = (Trade)xstream.fromXML(trade);
		return t;
	}	
	
	private String createTrade(Long id)
	{
		String result = new RestTemplate()
		.getForObject(
				"http://localhost:8080/create/trade/{id}",
				String.class, "1");
		return result;
	}
}
