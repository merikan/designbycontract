package com.dbc.mm.form;

import java.util.List;

import com.dbc.mm.model.Transaction;


public class DashboardForm {
	
	private List<Transaction> allTransactions;

	public List<Transaction> getAllTransactions() {
		return allTransactions;
	}

	public void setAllTransactions(List<Transaction> allTransactions) {
		this.allTransactions = allTransactions;
	}
	


}
