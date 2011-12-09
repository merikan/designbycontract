package com.dbc.service;

import com.dbc.domain.Trade;
import com.dbc.exception.CreateTradeException;
import com.dbc.exception.TradeNotFoundException;

public interface TradingService {

	Long createTrade(Trade trade) throws CreateTradeException;
	Trade getTradeById(Long id) throws TradeNotFoundException;

}
