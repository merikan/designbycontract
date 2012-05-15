package com.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.domain.Price;
import com.domain.Trade;
import com.repository.pricing.PricingRepository;
import com.service.pricing.PricingService;
import com.service.pricing.SimplePricingService;

public class PricingServiceUnitTest {

@Test
public void testGetTotalPriceForMultipleTrades() throws Exception {
	
	Price price1 = new Price(10.0); 
	Price price2 = new Price(15.0);
	Price price3 = new Price(25.0);
	
	PricingRepository stubbedPricingRepository = mock(PricingRepository.class);
	when(stubbedPricingRepository.getPriceForTrade(any(Trade.class)))
		.thenReturn(price1, price2, price3);
			
	PricingService service = new SimplePricingService(stubbedPricingRepository);	
	Price totalPrice = service.getTotalPriceForTrades(new TestDataHelper().getTrades());
	
	double total = price1.getAmount()+price2.getAmount()+ price3.getAmount();		
	assertEquals(total, totalPrice.getAmount(), 0);
}

@Test
public void testGetHighestPricedTrade() throws Exception {
	
	Price price1 = new Price(10.0); 
	Price price2 = new Price(15.0);
	Price price3 = new Price(25.0);
	
	PricingRepository pricingRepository = mock(PricingRepository.class);
	when(pricingRepository.getPriceForTrade(any(Trade.class))).thenReturn(price1, price2, price3);
			
	PricingService service = new SimplePricingService(pricingRepository);
	Price highestPrice = service.getHighestPricedTrade(new TestDataHelper().getTrades());
		
	assertEquals(price3.getAmount(), highestPrice.getAmount(), 0);
}

 	
}
