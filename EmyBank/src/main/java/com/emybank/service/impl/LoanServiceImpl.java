package com.emybank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emybank.entity.Customer;
import com.emybank.entity.Loan;
import com.emybank.model.LoanDTO;
import com.emybank.repository.LoanRepository;
import com.emybank.service.LoanService;

@Service
@Transactional
public class LoanServiceImpl implements LoanService{
	@Autowired
	LoanRepository loanRepository;
	
	@Override
	public void add(LoanDTO loanDTO) {
		// TODO Auto-generated method stub
		Loan loan = new Loan();
		Customer customer = new Customer();
		
		loan.setDateIssued(loanDTO.getDateIssued());
		loan.setDuration(loanDTO.getDuration());
		loan.setLoanAmount(loanDTO.getAmount());
		loan.setRate(loanDTO.getRate());
		customer.setId(loanDTO.getCustomerDTO().getId());
		loan.setCustomer(customer);
		
		loanRepository.add(loan);
	}
	
}
