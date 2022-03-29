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
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="bank")
	private String bank;
	
	@Column(name="to_account_id")
	private long toAccount;
	
	@Column(name="date_issued")
	private Date dateIssued;
	
	@Column(name="amount")
	private long amount;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(name = "from_account_id")
	private Account account;
}
