package com.dbc.service;

import com.dbc.model.Trade;

public interface TradeService {
	public Trade getTradeByReference(String reference);
	public Trade getTradeById(Long id);
	public Long createTrade(Trade t);
}
