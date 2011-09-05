package com.dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.model.Trade;
import com.dbc.repository.TradeRepository;

@Service
public class SimpleTradeService implements TradeService{

	@Autowired
	TradeRepository tradeRepository;	
	
	public SimpleTradeService()
	{
		
	}
	
	public SimpleTradeService(TradeRepository tradeRepository)
	{
		this.tradeRepository = tradeRepository;
	}
	
	@Override
	public Trade getTradeByReference(String reference) {
		return tradeRepository.getTradeByReference(reference);
	}

	@Override
	public Long createTrade(Trade t) {
		Long id = tradeRepository.createTrade(t);
		return id;
	}

}
