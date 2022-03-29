package com.emybank.repository;

import java.util.Date;
import java.util.List;

import com.emybank.entity.Transaction;

public interface TransactionRepository {
	public List<Transaction> findAll(Date dateStart, Date dateEnd, int start, int length);
	
	public void add(Transaction transaction);
	
	public int countAll();
	
	public int countByDate(Date from, Date to);
}
