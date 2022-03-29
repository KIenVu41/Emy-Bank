package com.emybank.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emybank.entity.Loan;
import com.emybank.repository.LoanRepository;

@Repository
@Transactional
public class LoanRepositoryImpl implements LoanRepository{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void add(Loan loan) {
		// TODO Auto-generated method stub
		entityManager.persist(loan);
	}

}
