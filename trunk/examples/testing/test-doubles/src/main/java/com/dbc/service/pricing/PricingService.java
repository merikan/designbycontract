package com.dbc.service.pricing;

import java.util.Collection;

import com.dbc.domain.Trade;
import com.dbc.domain.Price.Price;

public interface PricingService {
	
	public Price priceTrade(Trade trade);
	public Price getTotalPriceForTrades(Collection<Trade> trades);
	public Price getHighestPricedTrade(Collection<Trade> trades);

}
