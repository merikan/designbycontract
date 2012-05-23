package com.dbc.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.dbc.model.Trade;
import com.dbc.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
public class TradeServiceIntegrationTest {
	
	private TestHelper helper = new TestHelper();

	@Test
	public void testGetTradeById() throws Exception {		
		Trade dummyTrade = helper.getDummyTrade();
		Trade savedTrade = helper.createTrade(dummyTrade.getId());
		
		Trade trade = helper.getTradeById(dummyTrade.getId());		
		assertEquals(trade, savedTrade);
	}
}
