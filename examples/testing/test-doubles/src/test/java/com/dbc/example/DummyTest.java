package com.dbc.example;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Test;

import com.dbc.examples.Address;
import com.dbc.examples.AddressBook;
import com.dbc.examples.City;
import com.dbc.examples.Customer;
import com.dbc.examples.State;
import com.dbc.exception.InvalidCustomerException;

public class DummyTest {

	@Test
	public void addCustomerTest() throws Exception {
		State state = new State("West Dakota", "WD");
		City city = new City("Centreville", state);
		Address address = new Address("123 Blake St.", city);
		Customer customer = new Customer("john", "dobie", address);
		
		AddressBook addressBook = new AddressBook();
		addressBook.addCustomer(customer);

		Assert.assertEquals(1, addressBook.getNumberOfCustomers());
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
	     Assert.assertEquals(1, addressBook.getNumberOfCustomers());  
	 } 
}
