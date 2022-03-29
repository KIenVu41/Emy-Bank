package com.emybank.service;

import java.util.Date;
import java.util.List;

import com.emybank.model.TransactionDTO;

public interface TransactionService {
	public List<TransactionDTO> findAll(Date dateStart, Date dateEnd, int start, int length);
	public void add(TransactionDTO transactionDTO);
	public int countAll();
	public int countByDate(Date from, Date to);
}
