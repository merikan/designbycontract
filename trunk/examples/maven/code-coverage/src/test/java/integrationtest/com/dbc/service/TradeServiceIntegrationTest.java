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
	public void testCreateTradeFromRestService() throws Exception 
	{
		long id = helper.getRandomId();
		Trade t = helper.createTrade(id);
		assertEquals(t.getId(), id);
	}

	@Test
	public void testFindTrade() throws Exception {		
		long id = helper.getRandomId();
		Trade t = helper.createTrade(id);
		Trade trade = helper.findTrade(t.getId());		
		assertEquals(trade.getId(), id);
	}

}
