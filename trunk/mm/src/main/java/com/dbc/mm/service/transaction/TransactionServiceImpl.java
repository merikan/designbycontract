package com.dbc.mm.service.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbc.mm.model.Transaction;
import com.dbc.mm.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}
}
