package com.dbc.repository.trade;

import java.util.List;

import com.dbc.domain.Trade;
import com.dbc.exception.CreateTradeException;
import com.dbc.exception.TradeNotFoundException;

public interface TradeRepository {

	Long createTrade(Trade trade) throws CreateTradeException;

	Trade getTradeById(Long id) throws TradeNotFoundException;

	List<Trade> getTradesFilteredByReference(String reference) throws TradeNotFoundException;

}
