package com.dbc.repository;

import com.dbc.model.Trade;

public interface TradeRepository {

	public Trade getTradeByReference(String reference);

}
