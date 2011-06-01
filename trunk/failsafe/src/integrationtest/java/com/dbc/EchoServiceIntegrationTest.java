package com.dbc;

import org.junit.Test;
import static org.junit.Assert.*;

public class EchoServiceIntegrationTest {

	private static final String TEST_MESSAGE = "Integration Test";
	
	@Test
	public void emptyTest() throws Exception 
	{
		EchoService echoService = new EchoService();
		String message = echoService.echo(TEST_MESSAGE);
		assertEquals(message, TEST_MESSAGE); 
	}

}
