package com.dbc.mm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbc.mm.model.Account;
import com.dbc.mm.model.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	public List<Account> findByUser(User user);
	public Account findByUserAndDefaultAccount(User user, boolean defaultAccount);
	
	
}
