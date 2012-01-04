package com.dbc.domain.Price;


public class Price {

	final long amount;

	public Price()
	{
		this(0);
	}
	
	public Price(long amount)
	{
		this.amount = amount;
	}
	
	public Price add(Price price)
	{
		Price sumPrice = new Price(this.amount + price.getAmount());
		return sumPrice;
	}

	public long getAmount() {
		return amount;
	}
	
	
	
	
}
