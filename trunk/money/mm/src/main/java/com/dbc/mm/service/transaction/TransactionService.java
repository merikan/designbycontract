package com.dbc.mm.service.transaction;

import java.util.List;

import com.dbc.mm.model.Account;
import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;

public interface TransactionService {

	public List<Transaction> findAll();

	void save(List<Transaction> transactions);

	void save(Transaction t);
	
	public Transaction findById(Long id);
	public List<Transaction> findByCategory(Category category);

	List<Transaction> findByAccount(Account account);

	List<Transaction> findByAccountAndCategory(Account account, Category category);

}
