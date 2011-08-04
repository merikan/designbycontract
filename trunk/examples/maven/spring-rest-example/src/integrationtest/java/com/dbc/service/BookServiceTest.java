package com.dbc.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class BookServiceTest {

	@Test
	public void testName() throws Exception {
		String result = new RestTemplate()
				.getForObject(
						"http://localhost:8080/booksearch/books/{bookid}",
						String.class, "1");
		Assert.assertNotNull(result);
		System.out.println(result);
	}
}
