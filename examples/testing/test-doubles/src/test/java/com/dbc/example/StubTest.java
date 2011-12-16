package com.dbc.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.dbc.domain.Trade;
import com.dbc.exception.TradeNotFoundException;
import com.dbc.repository.TradeRepository;
import com.dbc.service.JdbcTradingService;
import com.dbc.service.TradingService;

public class StubTest {

	@Test
	public void testGetTradeById() throws Exception {
		Trade trade = new FixtureHelper().getTrade();

		TradeRepository tradeRepository = mock(TradeRepository.class);
		when(tradeRepository.getTradeById(anyLong())).thenReturn(trade);

		TradingService tradingService = new JdbcTradingService(tradeRepository);
		Trade databaseTrade = tradingService.getTradeById(trade.getId());
		assertEquals(trade, databaseTrade);
	}


	@Test(expected=TradeNotFoundException.class)
	public void testInvalidTrade() throws Exception {
		Trade trade = new FixtureHelper().getTrade();
		TradeRepository tradeRepository = mock(TradeRepository.class);

		when(tradeRepository.getTradeById(anyLong())).thenThrow(new TradeNotFoundException());

		TradingService tradingService = new JdbcTradingService(tradeRepository);
		tradingService.getTradeById(trade.getId());
	}
}
