package com.dbc.example;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dbc.domain.Trade;
import com.dbc.domain.Price.Price;
import com.dbc.repository.pricing.PricingRepository;
import com.dbc.service.pricing.PricingService;
import com.dbc.service.pricing.SimplePricingService;

public class StubTest {

private List<Trade> getTrades()
{
	List<Trade> tradeList = new ArrayList<Trade>();
	tradeList.add(new Trade(1L, "Trade 1 Description", "Reference 1"));
	tradeList.add(new Trade(2L, "Trade 2 Description", "Reference 2"));
	tradeList.add(new Trade(3L, "Trade 3 Description", "Reference 3"));
	return tradeList;
}
	
@Test
public void testGetTotalPriceForMultipleTrades() throws Exception {
	
	Price price1 = new Price(10); 
	Price price2 = new Price(15);
	Price price3 = new Price(25);
	
	PricingRepository stubbedPricingRepository = mock(PricingRepository.class);
	when(stubbedPricingRepository.getPriceForTrade(any(Trade.class)))
		.thenReturn(price1, price2, price3);
			
	PricingService service = new SimplePricingService(stubbedPricingRepository);
	Price totalPrice = service.getTotalPriceForTrades(getTrades());
	
	long total = price1.getAmount()+price2.getAmount()+ price3.getAmount();		
	assertEquals(total, totalPrice.getAmount());
}

@Test
public void testGetHighestPricedTrade() throws Exception {
	
	Price price1 = new Price(10); 
	Price price2 = new Price(15);
	Price price3 = new Price(25);
	
	PricingRepository pricingRepository = mock(PricingRepository.class);
	when(pricingRepository.getPriceForTrade(any(Trade.class))).thenReturn(price1, price2, price3);
			
	PricingService service = new SimplePricingService(pricingRepository);
	Price highestPrice = service.getHighestPricedTrade(getTrades());
		
	assertEquals(price3.getAmount(), highestPrice.getAmount());
}

 	
}
