package com.dbc;

public class EchoService {
	
	private int version = 0;
	private String name = "";

	public String echo(String message)
	{
		return message;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
