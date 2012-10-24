package com.dbc.mm.service.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;
import com.dbc.mm.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
	
	@Override
	public Transaction findById(Long id) {
		return transactionRepository.findOne(id);
	}
	
	@Override
	public void save(List<Transaction> transactions) {
		transactionRepository.save(transactions);
	}
	
	@Override
	public void save(Transaction t)
	{
		transactionRepository.save(t);
	}

	@Override
	public List<Transaction> findByCategory(Category category) {
		return transactionRepository.findByCategory(category);
	}
}

