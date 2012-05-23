package com.dbc.service;

import org.springframework.web.client.RestTemplate;

import com.dbc.model.Trade;
import com.thoughtworks.xstream.XStream;

public class TestHelper {

	private String ENDPOINT = "http://localhost:8081/code-coverage";

	public Trade createTrade(Long id) {
		String tradeXml = new RestTemplate().getForObject(ENDPOINT
				+ "/create/trade/{id}", String.class, id);
		return getTradeFromXml(tradeXml);
	}

	public Trade getTradeById(long id) {
		String tradeXml = new RestTemplate().getForObject(ENDPOINT
				+ "/find/trade/{id}", String.class, id);
		return getTradeFromXml(tradeXml);
	}

	public long getRandomId() {
		long id = (long) (Math.random() * 100000);
		return id;
	}

	public Trade getTradeFromXml(String trade) {
		XStream xstream = new XStream();
		xstream.processAnnotations(Trade.class);
		Trade t = (Trade) xstream.fromXML(trade);
		return t;
	}
	
	public Trade getDummyTrade()
	{
		Trade trade = new Trade(0L);
		String reference = "1234";
		trade.setReference(reference);
		return trade;
	}
}