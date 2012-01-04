package com.dbc.repository.pricing;

import java.util.List;

import com.dbc.domain.Trade;
import com.dbc.domain.Price.Price;

public interface PricingRepository {
	public Price getPriceForTrade(Trade trade);
	public List<Price> getPriceForListOfTrades(List<Trade> tradeList);

}
