package com.dbc.mm.util.aspects;

import java.util.Formatter;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProfilingAspect {

	protected static Logger logger = Logger.getLogger(ProfilingAspect.class);
	
	@Pointcut("execution(* com.dbc.mm.repository.*.*(..))")
	public void database() {
	}
	
	@Pointcut("execution(* com.dbc.mm.service.*.*(..))")
	public void services() {
	}

	@Pointcut("execution(* com.dbc.mm.controller.main.*.*(..))")
	public void controller() {
		logger.debug("Controller");
	}
	
	@Around("services()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		String message = getFormattedString("Service", elapsedTime, pjp.getSignature().toShortString());
		logger.debug(message);
		return output;
	}

	@Around("controller()")
	public Object profileController(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object output = pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		String message = getFormattedString("Controller", elapsedTime, pjp.getSignature().toShortString());
		logger.debug(message);
		return output;
	}
	
	public String getFormattedString(String type, long time, String signature)
	{
		   StringBuilder sb = new StringBuilder();
		   Formatter formatter = new Formatter(sb);
		   formatter.format("%10s %8s(ms) %s", type, time, signature);
		   return sb.toString();
	}
	
	public static void main(String args[])
	{
		System.out.println(new ProfilingAspect().getFormattedString("Controller", 10000, "public statuic  void main strings args"));
	}

}
