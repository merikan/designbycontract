package com.dbc.mm.vo;

public class ChartData {
	
	private String name;
	private float data;
	public ChartData(String name, float data) {
		super();
		this.name = name;
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getData() {
		return data;
	}
	public void setData(float data) {
		this.data = data;
	}

}
