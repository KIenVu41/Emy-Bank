package com.emybank.repository;

import com.emybank.entity.Account;

public interface AccountRepository {
	public void add(Account account);
	
	public void update(Account account);
	
	public void delete(Account account);
	
	public Account findById(long id);
	
	public Account findByUsername(String username);
}
