package com.dbc.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClasspathClassesFinderTest {

	@Test
	public void testName() throws Exception {		
		ClasspathClassesFinder finder = new ClasspathClassesFinder();
		Class[] classes = finder.getSuiteClassesWithAnnotation("com.dbc", Category.class);
		assertTrue(classes.length > 0);		
	}
	
}
