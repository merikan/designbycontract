package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.domain.Price;
import com.repository.pricing.FixedPriceRepository;
import com.repository.pricing.PricingRepository;
import com.service.pricing.PricingService;
import com.service.pricing.SimplePricingService;
import com.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
public class PricingServiceIntegrationTest {

	private PricingService pricingService;

	@Before
	public void setupPricingService() {
		PricingRepository repository = new FixedPriceRepository();
		pricingService = new SimplePricingService(repository);
	}

	@Test
	@Category(com.test.annotation.type.IntegrationTest.class)
	public void testGetHighestPricedTrade() throws Exception {

		Price price = pricingService.getHighestPricedTrade(new TestDataHelper().getTrades());
		assertEquals(price.getAmount(), 10.0, 0.0);
	}
	
	@Test
	@Category(com.test.annotation.type.IntegrationTest.class)
	public void testPricingForTrades() throws Exception {

		Price price = pricingService.getTotalPriceForTrades(new TestDataHelper().getTrades());
		assertEquals(price.getAmount(), 30.0, 0.0);
	}		
}
