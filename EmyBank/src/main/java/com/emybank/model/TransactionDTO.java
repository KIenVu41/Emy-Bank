package com.emybank.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	private long id;
	private String bank;
	private long toAccount;
	private Date dateIssued;
	private long amount;
	private AccountDTO accountDTO;
}
