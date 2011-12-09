package com.dbc.util;

public class SequenceGenerator {

	private static Long id = 0L;
	
	public Long getNextSequenceId()
	{
		return id++;
	}
	
}
