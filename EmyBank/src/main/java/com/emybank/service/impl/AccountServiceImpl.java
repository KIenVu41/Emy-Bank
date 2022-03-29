package com.emybank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emybank.entity.Account;
import com.emybank.entity.Customer;
import com.emybank.model.AccountDTO;
import com.emybank.model.CustomerDTO;
import com.emybank.repository.AccountRepository;
import com.emybank.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void add(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		Account account = new Account();
		
		account.setBalance(accountDTO.getBalance());
		account.setCurrency(accountDTO.getCurrency());
		account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
		account.setRole(accountDTO.getRole());
		account.setStatus(accountDTO.getStatus());
		account.setType(accountDTO.getType());
		account.setUsername(accountDTO.getUsername());
		
		accountRepository.add(account);
	}

	@Override
	public void update(AccountDTO accountDTO) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(accountDTO.getId());
		Customer customer = new Customer();
		if(account != null) {
			account.setId(accountDTO.getId());
			account.setBalance(accountDTO.getBalance());
			account.setCurrency(accountDTO.getCurrency());
			account.setPassword(accountDTO.getPassword());
			account.setRole(accountDTO.getRole());
			account.setStatus(accountDTO.getStatus());
			account.setType(accountDTO.getType());
			account.setUsername(accountDTO.getUsername());
			customer.setId(accountDTO.getCustomerDTO().getId());
//			customer.setAddress(accountDTO.getCustomerDTO().getAddress());
//			customer.setCity(accountDTO.getCustomerDTO().getCity());
//			customer.setEmail(accountDTO.getCustomerDTO().getEmail());
//			customer.setFirstName(accountDTO.getCustomerDTO().getFirstName());
//			customer.setLast_name(accountDTO.getCustomerDTO().getLastName());
//			customer.setMobile(accountDTO.getCustomerDTO().getMobile());
			account.setCustomer(customer);
			accountRepository.update(account);
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id);
		if(account != null) {
			accountRepository.delete(account);
		}
	}

	@Override
	public AccountDTO findById(long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id);
		AccountDTO accountDTO = new AccountDTO();
		
		accountDTO.setBalance(account.getBalance());
		accountDTO.setCurrency(account.getCurrency());
		accountDTO.setId(account.getId());
		accountDTO.setPassword(account.getPassword());
		accountDTO.setRole(account.getRole());
		accountDTO.setStatus(account.getStatus());
		accountDTO.setType(account.getType());
		accountDTO.setUsername(account.getUsername());
		return accountDTO;
	}

	@Override
	public AccountDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findByUsername(username);
		AccountDTO accountDTO = new AccountDTO();
		CustomerDTO customerDTO = new CustomerDTO();
		
		accountDTO.setBalance(account.getBalance());
		accountDTO.setCurrency(account.getCurrency());
		accountDTO.setId(account.getId());
		accountDTO.setPassword(account.getPassword());
		accountDTO.setRole(account.getRole());
		accountDTO.setStatus(account.getStatus());
		accountDTO.setType(account.getType());
		accountDTO.setUsername(account.getUsername());
		customerDTO.setId(account.getCustomer().getId());
		customerDTO.setAddress(account.getCustomer().getAddress());
		customerDTO.setCity(account.getCustomer().getCity());
		customerDTO.setEmail(account.getCustomer().getEmail());
		customerDTO.setFirstName(account.getCustomer().getFirstName());
		customerDTO.setLastName(account.getCustomer().getLast_name());
		customerDTO.setMobile(account.getCustomer().getMobile());
		accountDTO.setCustomerDTO(customerDTO);
		return accountDTO;
	}

}
