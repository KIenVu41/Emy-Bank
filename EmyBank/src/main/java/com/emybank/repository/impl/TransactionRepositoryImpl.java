package com.emybank.repository.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.emybank.entity.Transaction;
import com.emybank.repository.TransactionRepository;

@Repository
@Transactional
public class TransactionRepositoryImpl implements TransactionRepository{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void add(Transaction transaction) {
		// TODO Auto-generated method stub
		entityManager.persist(transaction);
	}

	@Override
	public List<Transaction> findAll(Date dateStart, Date dateEnd, int start, int length) {
		// TODO Auto-generated method stub
		if(dateStart == null || dateEnd == null) {
			String jql = "select t from Transaction t join t.account a";
			Query query = entityManager.createQuery(jql, Transaction.class);
			query.setFirstResult(start).setMaxResults(length);
			
			return query.getResultList();
		}else {
			String jql = "select t from Transaction t join t.account a where t.dateIssued BETWEEN :start AND :end";
			Query query = entityManager.createQuery(jql, Transaction.class);
			query.setParameter("start", dateStart);
			query.setParameter("end", dateEnd);
			query.setFirstResult(start).setMaxResults(length);
			return query.getResultList();}
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT COUNT(t) FROM Transaction t" );
		return query.getSingleResult() != null ? Integer.parseInt(query.getSingleResult().toString()) : 0;
	}

	@Override
	public int countByDate(Date from, Date to) {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("SELECT COUNT(t) FROM Transaction t where t.dateIssued BETWEEN :start AND :end" );
		query.setParameter("start", from);
		query.setParameter("end", to);
		return query.getSingleResult() != null ? Integer.parseInt(query.getSingleResult().toString()) : 0;
	}

}
