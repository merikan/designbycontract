package com.service.pricing;

import java.util.Collection;

import com.domain.Price;
import com.domain.Trade;

public interface PricingService {
	
	Price priceTrade(Trade trade);
	Price getTotalPriceForTrades(Collection<Trade> trades);
	Price getHighestPricedTrade(Collection<Trade> trades);

}
