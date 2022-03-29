package com.emybank.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="account_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="balance")
	private long balance;
	
	@Column(name="account_status")
	private boolean status;
	
	@Column(name="account_type")
	private String type;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	public boolean getStatus() {
		return status;
	}
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "customer_id") 
	private Customer customer;
}
