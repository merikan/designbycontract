package com.repository.pricing;

import java.util.ArrayList;
import java.util.List;

import com.domain.Price;
import com.domain.Trade;

public class FixedPriceRepository implements PricingRepository{

	private static final double FIXED_PRICE = 10.0;
	
	@Override
	public Price getPriceForTrade(Trade trade) {		
		return new Price(FIXED_PRICE);
	}

	@Override
	public List<Price> getPriceForListOfTrades(List<Trade> tradeList) {
		List<Price> prices = new ArrayList<Price>();
		for (Trade t : tradeList)
		{
			Price p = getPriceForTrade(t);
			prices.add(p);
		}
		return prices;
	}

}
