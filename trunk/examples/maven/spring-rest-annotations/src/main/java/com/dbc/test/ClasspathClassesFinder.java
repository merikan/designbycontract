package com.dbc.test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClasspathClassesFinder {

	public static Logger logger = Logger.getLogger(ClasspathClassesFinder.class);
	
	/**
	 * Get the list of classes of a given package name, and that are annotated
	 * by a given annotation.
	 * 
	 * @param packageName
	 *            The package name of the classes.
	 * @param testAnnotation
	 *            The annotation the class should be annotated with.
	 * @return The List of classes that matches the requirements.
	 */
	public Class<?>[] getSuiteClassesWithAnnotation(String packageName,
			Class<? extends Annotation> annotation) {
		try {
			Class<?>[] allClasses = getClasses(packageName);				
			return getAnnotatedClasses(allClasses, annotation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Class<?>[] getSuiteClassesWithNoAnnotation(String packageName) {
		try {
			Class<?>[] allClasses = getClasses(packageName);				
			return getClassesWithNoAnnotations(allClasses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	Class<?>[] getAnnotatedClasses(Class<?>[] allClasses, Class<? extends Annotation> annotation)
	{
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (Class<?> c : allClasses)
		{
			logger.debug("Found File [" + c +  "]");
			// Does the file is annotated with the given annotation?
			printAnnotations(c);		
			Annotation a = c.getAnnotation(annotation);
			if (a != null) {
				logger.debug("Adding Class [" + c +  "]");
				annotatedClasses.add(c);
			}
		}		
		return annotatedClasses.toArray(new Class[annotatedClasses.size()]);
	}
	
	Class<?>[] getClassesWithNoAnnotations(Class<?>[] allClasses)
	{
		List<Class<?>> annotatedClasses = new ArrayList<Class<?>>();
		for (Class<?> c : allClasses)
		{
			logger.debug("Found File [" + c +  "]");
			// Does the file is annotated with the given annotation?
			printAnnotations(c);		
			Annotation a = c.getAnnotation(Category.class);	
			
			if (a == null) {
				printAnnotations(c);
				if (isTestClass(c))
				{
					logger.debug("Adding Class [" + c +  "]");
					annotatedClasses.add(c);
				}
			}
		}		
		return annotatedClasses.toArray(new Class[annotatedClasses.size()]);
	}
	
	
	private boolean isTestClass(Class<?> c) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods)
		{
			if (method.isAnnotationPresent(Test.class))
			{
				logger.debug("****************");
				return true;
			}
		}		
		return false;
	}

	/**
	 * Get the list of classes of a given package name, and that are annotated
	 * by a given annotation.
	 * 
	 * @param packageName
	 *            The package name of the classes.
	 * @param annotation
	 *            The annotation the class should be annotated with.
	 * @return The List of classes that matches the requirements.
	 * @throws ClassNotFoundException
	 *             If something goes wrong...
	 * @throws IOException
	 *             If something goes wrong...
	 */
	private Class<?>[] getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String path = packageName.replace('.', '/');
		// Get classpath
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		// For each classpath, get the classes.
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : dirs) {
			classes.addAll(findAllClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Find classes, in a given directory (recursively), for a given package
	 * name, that are annotated by a given annotation.
	 * 
	 * @param directory
	 *            The directory where to look for.
	 * @param packageName
	 *            The package name of the classes.
	 * @param annotation
	 *            The annotation the class should be annotated with.
	 * @return The List of classes that matches the requirements.
	 * @throws ClassNotFoundException
	 *             If something goes wrong...
	 */
	private List<Class<?>> findAllClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				logger.debug("Found Directory [" + file + "]");
				classes.addAll(findAllClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				// We remove the .class at the end of the filename to get the
				// class name...
				Class<?> c = Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6));

				logger.debug("Found File [" + c + "]");
				// Does the file is annotated with the given annotation?

				classes.add(c);
			}
		}
		return classes;
	}

	private void printAnnotations(Class<?> c) {
		Annotation[] annotations = c.getAnnotations();
		for (Annotation a : annotations) {
			logger.debug("Annotation [" + a.annotationType() + "]");
		}

		annotations = c.getDeclaredAnnotations();
		for (Annotation a : annotations) {
			logger.debug("Annotation [" + a.annotationType() + "]");
		}

	}
}