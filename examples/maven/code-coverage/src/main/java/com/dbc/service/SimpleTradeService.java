package com.dbc.service;

import org.springframework.stereotype.Service;

import com.dbc.model.Trade;
import com.dbc.repository.TradeRepository;

@Service
public class SimpleTradeService implements TradeService{

	TradeRepository tradeRepository;	

	public SimpleTradeService()
	{
		super();
	}
	
	public SimpleTradeService(TradeRepository tradeRepository)
	{
		this.tradeRepository = tradeRepository;
	}
	
	@Override
	public Trade findTradeById(Long id) {
		return tradeRepository.getTradeById(id);
	}
	
	@Override
	public Long createTrade(Trade t) {
		Long id = tradeRepository.createTrade(t);
		return id;
	}
}
