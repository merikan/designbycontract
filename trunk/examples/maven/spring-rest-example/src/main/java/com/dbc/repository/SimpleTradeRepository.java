package com.dbc.repository;

import org.springframework.stereotype.Service;

import com.dbc.model.Trade;

@Service
public class SimpleTradeRepository implements TradeRepository{

	
	
	public Trade getTradeByReference(String reference)
	{
		Trade trade = new Trade(0L);
		trade.setReference(reference);
		return trade;	
	}

	@Override
	public Long createTrade(Trade t) {
		return null;
	}

}
