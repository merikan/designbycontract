package com.dbc.mm.form;

import java.util.List;

import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;


public class TransactionForm {
	
	private List<Transaction> allTransactions;
	public List<Category> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}

	private List<Category> allCategories;

	public List<Transaction> getAllTransactions() {
		return allTransactions;
	}

	public void setAllTransactions(List<Transaction> allTransactions) {
		this.allTransactions = allTransactions;
	}
	


}
