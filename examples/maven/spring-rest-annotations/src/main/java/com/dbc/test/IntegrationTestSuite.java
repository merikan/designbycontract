package com.dbc.test;

import org.junit.experimental.categories.Category;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;


public class IntegrationTestSuite extends Suite{

	public IntegrationTestSuite(Class<?> clazz, RunnerBuilder builder)
			throws InitializationError {
		this(builder, clazz, new ClasspathClassesFinder().getSuiteClassesWithAnnotation("com.dbc", Category.class));
	}

	public IntegrationTestSuite(RunnerBuilder builder, Class<?> clazz,
			Class<?>[] suiteClasses) throws InitializationError {
		super(builder, clazz, suiteClasses);
	}

}
