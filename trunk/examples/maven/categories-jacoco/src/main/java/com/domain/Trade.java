package com.domain;


public class Trade {

	private Long id;

	private String reference;

	private String description;
	

	public Trade(Long id, String reference, String description) {
		super();
		this.id = id;
		this.reference = reference;
		this.description = description;
	}

	public Trade(String reference, String description) {
		this(null, reference, description);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getReference() {
		return reference;
	}

	public String getDescription() {
		return description;
	}
}
