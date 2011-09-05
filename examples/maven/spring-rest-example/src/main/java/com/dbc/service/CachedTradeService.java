package com.dbc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dbc.model.Trade;
import com.dbc.repository.TradeRepository;

public class CachedTradeService implements TradeService {

	private Map<String, Trade> tradeCache = new HashMap<String, Trade>();
	private TradeRepository tradeRepository;	
	
	public CachedTradeService(TradeRepository tradeRepository)
	{
		this.tradeRepository = tradeRepository;
	}	
	
	@Override
	public Trade getTradeByReference(String reference) {
		if (tradeCache.containsKey(reference))
		{
			return tradeCache.get(reference);
		}
		Trade trade = tradeRepository.getTradeByReference(reference);
		tradeCache.put(reference, trade);
		return trade;
	}

	@Override
	public Long createTrade(Trade t) {
		long id = tradeRepository.createTrade(t);
		return id;
	}

}
