package com.dbc.example;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.dbc.domain.Trade;
import com.dbc.repository.trade.TradeRepository;
import com.dbc.service.audit.AuditService;
import com.dbc.service.trading.SimpleTradingService;
import com.dbc.service.trading.TradingService;

@RunWith(MockitoJUnitRunner.class)
public class MockTest {

	@Mock
	TradeRepository tradeRepository;
	
	@Mock
	AuditService auditService;
		
	@Test
	public void testAuditLogEntryMadeForNewTrade() throws Exception {
		
		Trade trade = new Trade("Ref 1", "Description 1");
		when(tradeRepository.createTrade(trade)).thenReturn(anyLong());	
		
		TradingService tradingService = new SimpleTradingService(tradeRepository, auditService);
		tradingService.createTrade(trade);
		
		verify(auditService).logNewTrade(trade);
	}

}
