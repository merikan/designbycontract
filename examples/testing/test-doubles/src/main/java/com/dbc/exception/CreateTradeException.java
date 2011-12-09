package com.dbc.exception;

@SuppressWarnings("serial")
public class CreateTradeException extends RuntimeException {

	public CreateTradeException()
	{
		super();
	}
	
	public CreateTradeException(Exception e)
	{
		super(e);
	}

}
