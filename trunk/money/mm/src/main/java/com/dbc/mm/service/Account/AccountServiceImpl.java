package com.dbc.mm.service.Account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbc.mm.model.Account;
import com.dbc.mm.model.User;
import com.dbc.mm.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public List<Account> findByUser(User user) {
		return accountRepository.findByUser(user);
	}
	
	@Override
	public Account findOne(Long accountId) {
		return accountRepository.findOne(accountId);
	}

	@Override
	public Account getDefaultAccount(User user) {
		return accountRepository.findByUserAndDefaultAccount(user, true);
	}
}
