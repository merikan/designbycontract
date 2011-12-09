package com.dbc.examples;

import java.util.ArrayList;
import java.util.List;

import com.dbc.exception.InvalidCustomerException;

public class AddressBook {

	private List<Customer> customers = new ArrayList<Customer>();

	public AddressBook() {

	}

	public void addCustomer(Customer customer) {
		if (customer == null)
			throw new InvalidCustomerException();

		customers.add(customer);
	}

	public int getNumberOfCustomers() {
		return customers.size();
	}
}
