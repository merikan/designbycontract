package com.dbc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class EchoServiceUnitTest {

	private static final String TEST_MESSAGE = "Unit Test";
	
	@Test
	public void emptyTest() throws Exception 
	{
		EchoService echoService = new EchoService();
		String message = echoService.echo(TEST_MESSAGE);
		assertEquals(message, TEST_MESSAGE); 
	}
}