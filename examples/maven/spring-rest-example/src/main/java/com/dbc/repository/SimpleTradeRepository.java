package com.dbc.repository;

import com.dbc.model.Trade;

public class SimpleTradeRepository implements TradeRepository{

	public Trade getTradeByReference(String reference)
	{
		Trade trade = new Trade(reference);
		return trade;	
	}

}
