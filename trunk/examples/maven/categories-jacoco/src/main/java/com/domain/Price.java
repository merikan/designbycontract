package com.domain;

public class Price {

	private final double price;
	
	public Price(double price)
	{
		this.price = price;
	}

	public double getAmount() {
		return price;
	}

	public final Price add(Price price) {
		double totalPrice = this.price + price.getAmount();
		return new Price(totalPrice);
	}
}
