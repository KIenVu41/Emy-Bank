package com.emybank.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="loan_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="loan_amount")
	private long loanAmount;
	
	@Column(name="interest_rate")
	private float rate;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="date_issued")
	private Date dateIssued;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
