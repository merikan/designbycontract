package com.dbc.test;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;


public class UnitTestSuite extends Suite{

	public UnitTestSuite(Class<?> clazz, RunnerBuilder builder)
			throws InitializationError {
		this(builder, clazz, new ClasspathClassesFinder().getSuiteClassesWithNoAnnotation("com.dbc"));
	}

	public UnitTestSuite(RunnerBuilder builder, Class<?> clazz,
			Class<?>[] suiteClasses) throws InitializationError {
		super(builder, clazz, suiteClasses);
	}

}
