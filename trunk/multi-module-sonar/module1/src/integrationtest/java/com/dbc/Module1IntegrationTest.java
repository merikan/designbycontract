package com.dbc;

import org.junit.Test;

public class Module1IntegrationTest {

	@Test
	public void simpleTest() throws Exception 
	{
		Module1JavaClass jc = new Module1JavaClass();
		jc.setName("name");
		jc.setMessage("message");
	}

}
