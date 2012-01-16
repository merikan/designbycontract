package com.dbc.service.trading;

import java.util.List;

import com.dbc.domain.Trade;
import com.dbc.exception.CreateTradeException;
import com.dbc.exception.TradeNotFoundException;
import com.dbc.repository.trade.TradeRepository;
import com.dbc.service.audit.AuditService;

public class SimpleTradingService implements TradingService{

	TradeRepository tradeRepository;
	AuditService auditService;
	
	public SimpleTradingService(TradeRepository tradeRepository, AuditService auditService)
	{
		this.tradeRepository = tradeRepository;
		this.auditService = auditService;
	}
	
	public Long createTrade(Trade trade) throws CreateTradeException {
		Long id = tradeRepository.createTrade(trade);
		auditService.logNewTrade(trade);
		return id;
	}

	public Trade getTradeById(Long id) throws TradeNotFoundException {		
		Trade t = tradeRepository.getTradeById(id);
		return t;
	}

	public List<Trade> getTradesFilteredByReference(String reference) {
		// Long running call taking 30s.
		List<Trade> filteredTrades = tradeRepository.getTradesFilteredByReference(reference);
		return filteredTrades;
	}

}
