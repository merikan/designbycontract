package com.dbc.service.trading;

import java.util.List;

import com.dbc.domain.Trade;
import com.dbc.exception.CreateTradeException;
import com.dbc.exception.TradeNotFoundException;
import com.dbc.repository.trade.TradeRepository;

public class JdbcTradingService implements TradingService{

	TradeRepository tradeRepository;
	
	public JdbcTradingService(TradeRepository tradeRepository)
	{
		this.tradeRepository = tradeRepository;
	}
	
	public Long createTrade(Trade trade) throws CreateTradeException {
		return tradeRepository.createTrade(trade);
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
