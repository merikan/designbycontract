package com.dbc.service.pricing;

import java.util.Collection;

import com.dbc.domain.Trade;
import com.dbc.domain.Price.Price;
import com.dbc.repository.pricing.PricingRepository;

public class SimplePricingService implements PricingService
{	
	PricingRepository repository;

	public SimplePricingService(PricingRepository pricingRepository) {
		this.repository = pricingRepository;
	}

	@Override
	public Price priceTrade(Trade trade) {
		return repository.getPriceForTrade(trade);
	}

	@Override
	public Price getTotalPriceForTrades(Collection<Trade> trades) {
		Price totalPrice = new Price();
		for (Trade trade : trades)
		{
			Price tradePrice = repository.getPriceForTrade(trade);
			totalPrice = totalPrice.add(tradePrice);
		}
		return totalPrice;
	}
		
	/** 
	 * returns the highest priced trade in a collection.
	 * if there are more than one trades equal to the highest price return the first. 
	 */
@Override
public Price getHighestPricedTrade(Collection<Trade> trades) {
	Price highestPrice = new Price();		
	for (Trade trade : trades)
	{
		Price tradePrice = repository.getPriceForTrade(trade);
		if (tradePrice.getAmount() > highestPrice.getAmount())
		{
			highestPrice = tradePrice;
		}
	}
	return highestPrice;
}
}
