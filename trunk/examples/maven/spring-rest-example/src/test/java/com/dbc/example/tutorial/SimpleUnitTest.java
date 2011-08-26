package com.dbc.example.tutorial;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import com.dbc.model.Trade;
import com.dbc.service.TradeService;

@RunWith(MockitoJUnitRunner.class)
public class SimpleUnitTest {

	@Mock
	TradeService tradeService;
	
	// Does nothing really :-)
	@Test
	public void testGetTradeByReference() throws Exception
	{		
		Trade t = getTrade();		
		when(tradeService.getTradeByReference(t.getReference())).thenReturn(t);
		Trade t2 = tradeService.getTradeByReference(t.getReference());
		assertEquals(t, t2);
	}
	
	private Trade getTrade()
	{
		String reference = "1234";
		return new Trade(reference);
	}
	
}
