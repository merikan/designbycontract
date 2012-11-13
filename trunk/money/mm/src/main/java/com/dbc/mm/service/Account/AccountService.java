package com.dbc.mm.service.Account;

import java.util.List;

import com.dbc.mm.model.Account;
import com.dbc.mm.model.User;

public interface AccountService {

	public List<Account> findAll();

	Account save(Account account);

	List<Account> findByUser(User user);	

}
