package com.emybank.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emybank.entity.Account;
import com.emybank.entity.Transaction;
import com.emybank.model.AccountDTO;
import com.emybank.model.TransactionDTO;
import com.emybank.repository.TransactionRepository;
import com.emybank.service.TransactionService;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public void add(TransactionDTO transactionDTO) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		Account account = new Account();
		
		transaction.setAmount(transactionDTO.getAmount());
		transaction.setBank(transactionDTO.getBank());
		transaction.setDateIssued(transactionDTO.getDateIssued());
		transaction.setToAccount(transactionDTO.getToAccount());
		account.setId(transactionDTO.getAccountDTO().getId());
		transaction.setAccount(account);
		
		transactionRepository.add(transaction);
	}

	@Override
	public List<TransactionDTO> findAll(Date dateStart, Date dateEnd, int start, int length) {
		// TODO Auto-generated method stub
		List<Transaction> transactions = transactionRepository.findAll(dateStart, dateEnd, start, length);
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		
		for(Transaction t : transactions) {
			TransactionDTO transactionDTO = new TransactionDTO();
			AccountDTO accountDTO = new AccountDTO();
			
			transactionDTO.setId(t.getId());
			transactionDTO.setAmount(t.getAmount());
			transactionDTO.setBank(t.getBank());
			transactionDTO.setDateIssued(t.getDateIssued());
			transactionDTO.setToAccount(t.getToAccount());
			accountDTO.setBalance(t.getAccount().getBalance());
			accountDTO.setId(t.getAccount().getId());
			transactionDTO.setAccountDTO(accountDTO);
			
			transactionDTOs.add(transactionDTO);
		}
		return transactionDTOs;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return transactionRepository.countAll();
	}

	@Override
	public int countByDate(Date from, Date to) {
		// TODO Auto-generated method stub
		return transactionRepository.countByDate(from, to);
	}

}
