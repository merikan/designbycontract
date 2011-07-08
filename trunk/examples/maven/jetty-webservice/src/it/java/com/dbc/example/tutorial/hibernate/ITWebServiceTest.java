package com.dbc.example.tutorial.hibernate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.spring.HelloWorld;

public class ITWebServiceTest {
	private static String MESSAGE = "John Dobie";

	@Test
	public void testname() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("wsclient.xml");
		HelloWorld client = (HelloWorld) ctx.getBean("helloClient");		
		assertEquals("Hello " + MESSAGE, client.sayHi(MESSAGE));
	}
	
}
