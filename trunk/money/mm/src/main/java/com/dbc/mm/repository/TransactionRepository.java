package com.dbc.mm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbc.mm.model.Category;
import com.dbc.mm.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	public List<Transaction> findByCategory(Category category);

}
