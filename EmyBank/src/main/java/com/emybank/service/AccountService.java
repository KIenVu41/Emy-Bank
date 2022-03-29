package com.emybank.service;

import com.emybank.model.AccountDTO;

public interface AccountService {
	public void add(AccountDTO accountDTO);
	
	public void update(AccountDTO accountDTO);
	
	public void delete(long id);
	
	public AccountDTO findById(long id);
	
	public AccountDTO findByUsername(String username);
}
