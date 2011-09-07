package com.dbc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.dbc.model.Trade;
import com.thoughtworks.xstream.XStream;

public class TradeServiceIntegrationTest {
	
	@Test
	public void testCreateTradeFromRestService() throws Exception {
		long id = 1L;
		String tradeXml = createTrade(id);
		Trade t = getTradeFromXml(tradeXml);
		assertEquals(t.getId(), id);
	}

	@Test
	public void testGetTradeFromRestService() throws Exception {
		long id = 10L;
		createTrade(id);
		String tradeXml = new RestTemplate()
				.getForObject(
						"http://localhost:8080/find/trade/{id}",
						String.class, id);
		
		System.out.println(tradeXml);
		Trade trade = getTradeFromXml(tradeXml);
		assertEquals(trade.getId(), id);
	}

	private Trade getTradeFromXml(String trade) {
		XStream xstream = new XStream();
		xstream.processAnnotations(Trade.class);
		Trade t = (Trade)xstream.fromXML(trade);
		return t;
	}	
	
	private String createTrade(Long id)
	{
		String tradeXml = new RestTemplate()
		.getForObject(
				"http://localhost:8080/create/trade/{id}",
				String.class, id);
		return tradeXml;
	}
}
