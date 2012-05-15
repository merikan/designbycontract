package com.repository.pricing;

import java.util.List;

import com.domain.Price;
import com.domain.Trade;

public interface PricingRepository {
	Price getPriceForTrade(Trade trade);
	List<Price> getPriceForListOfTrades(List<Trade> tradeList);

}
