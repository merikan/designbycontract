package com.dbc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("trade")
@Entity
public class Trade {

	@Id	
	private Long id;
	private String reference;
	private String description;

	public Trade(Long id)
	{
		this.id = id;
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
}
