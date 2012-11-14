package com.dbc.mm.vo;

import java.math.BigDecimal;
import java.util.List;

import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;

public class ReportCategory implements Comparable<ReportCategory>{
	
	private Category category;
	private List<Transaction> transactions;
	private BigDecimal total;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	@Override
	public int compareTo(ReportCategory o) {
		return this.getTotal().compareTo(o.getTotal());
	}


	

}
