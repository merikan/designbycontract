package com.dbc.repository;

import com.dbc.model.Trade;

public interface TradeRepository {

	public Long createTrade(Trade t);
	public Trade getTradeByReference(String reference);

}
