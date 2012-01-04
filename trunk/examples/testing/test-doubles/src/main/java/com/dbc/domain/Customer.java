package com.dbc.domain;

public class Customer {
	private String firstname;
	private String surname;
	private Address address;
	
	public Customer(String firstname, String surname, Address address) {
		super();
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
