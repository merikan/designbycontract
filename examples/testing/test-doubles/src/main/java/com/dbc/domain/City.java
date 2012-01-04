package com.dbc.domain;

public class City {

	private String name;
	private County state;
	
	public City(String name, County state) {
		this.name = name;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public County getState() {
		return state;
	}

	public void setState(County state) {
		this.state = state;
	}

}
