package com.emybank.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emybank.entity.Account;
import com.emybank.repository.AccountRepository;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void add(Account account) {
		// TODO Auto-generated method stub
		entityManager.persist(account);
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		entityManager.merge(account);
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		entityManager.remove(account);
	}

	@Override
	public Account findById(long id) {
		// TODO Auto-generated method stub
		return entityManager.find(Account.class, id);
	}

	@Override
	public Account findByUsername(String username) {
		// TODO Auto-generated method stub
		String jql = "SELECT a FROM Account a join a.customer c WHERE a.username = ?1";
		return entityManager.createQuery(jql, Account.class).setParameter(1, username).getSingleResult();
	}

}
