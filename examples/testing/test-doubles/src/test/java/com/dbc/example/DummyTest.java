package com.dbc.example;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;
import org.junit.Test;

import com.dbc.domain.Address;
import com.dbc.domain.AddressBook;
import com.dbc.domain.City;
import com.dbc.domain.County;
import com.dbc.domain.Customer;
import com.dbc.exception.InvalidCustomerException;

public class DummyTest {
	
	public Customer createDummyCustomer() {
		County county = new County("Essex");
		City city = new City("Romford", county);
		Address address = new Address("1234 Bank Street", city);
		Customer customer = new Customer("john", "dobie", address);
		return customer;
	}

	@Test
	public void addCustomerTest() {
		Customer dummy = createDummyCustomer();
		AddressBook addressBook = new AddressBook();
		addressBook.addCustomer(dummy);
		assertEquals(1, addressBook.getNumberOfCustomers());
	}

	@Test(expected = InvalidCustomerException.class)
	public void addNullCustomerTest() {
		Customer dummy = null;
		AddressBook addressBook = new AddressBook();
		addressBook.addCustomer(dummy);
	}
	
	@Test 
	 public void addCustomerWithDummyTest() {  
	     Customer dummy = mock(Customer.class);  
	     AddressBook addressBook = new AddressBook();  
	     addressBook.addCustomer(dummy);  
	     assertEquals(1, addressBook.getNumberOfCustomers());  
	 } 
}
