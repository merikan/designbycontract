package com.test;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

@Category(IntegrationTest.class)
public class AnotherEmptyIntegrationTest {

	@Test
	public void emptyTest1() throws Exception {
		System.out.println();
	}
}
