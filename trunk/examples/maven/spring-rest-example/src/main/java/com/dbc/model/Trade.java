package com.dbc.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("trade")
public class Trade {
	
	public Trade(String reference)
	{
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String reference;
	private String description;
}
