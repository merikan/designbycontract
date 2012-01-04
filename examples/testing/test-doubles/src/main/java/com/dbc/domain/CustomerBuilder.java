package com.dbc.domain;

public class CustomerBuilder {

	private String firstname;
	private String surname;
	private Address address = new AddressBuilder().build();
	private County state;
	private City city;
	
	

	public CustomerBuilder withAddress(Address address)
	{
		this.address = address;
		return this;
	}
	
	
	public CustomerBuilder withFirstname(String firstname)
	{
		this.firstname = firstname;
		return this;
	}

	public CustomerBuilder withSurname(String surname)
	{
		this.surname = surname;
		return this;
	}
	
	
	public Customer build()
	{				
		Customer customer = new Customer(firstname, surname, address);
		return customer;		
	}
	
}
