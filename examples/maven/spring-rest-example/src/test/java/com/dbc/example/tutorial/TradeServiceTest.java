package com.dbc.example.tutorial;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dbc.model.Trade;
import com.dbc.repository.TradeRepository;
import com.dbc.service.SimpleTradeService;
import com.dbc.service.TradeService;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

	@Mock
	TradeRepository tradeRepository;
			
	@Test
	public void testSimpleTradeServiceGetTradeByReference() throws Exception
	{		
		Trade t = getTrade();				
		when(tradeRepository.getTradeByReference(t.getReference())).thenReturn(t);
		TradeService tradeService = new SimpleTradeService(tradeRepository);
		
		Trade t2 = tradeService.getTradeByReference(t.getReference());
		assertEquals(t, t2);
	}
	
	private Trade getTrade()
	{
		String reference = "1234";
		return new Trade(reference);
	}
	
}
