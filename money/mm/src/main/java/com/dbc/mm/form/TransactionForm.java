package com.dbc.mm.form;

import java.util.List;

import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.vo.ReportCategory;

public class TransactionForm {

	private List<Transaction> allTransactions;
	private List<ReportCategory> categories;
	private List<Category> allCategories;

	public List<Category> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}

	public List<ReportCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ReportCategory> allCategories) {
		this.categories = allCategories;
	}

	public List<Transaction> getAllTransactions() {
		return allTransactions;
	}

	public void setAllTransactions(List<Transaction> allTransactions) {
		this.allTransactions = allTransactions;
	}
}
