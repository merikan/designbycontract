package com.dbc.service;

import org.springframework.stereotype.Service;

import com.dbc.model.Trade;

@Service
public class TradeServiceImpl implements TradeService{

	@Override
	public Trade getTradeByReference(String reference) {
		Trade trade = new Trade();
		trade.setReference(reference);
		return trade;
	}

}
