package com.emybank.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {
	private long id;
	private long amount;
	private float rate;
	private int duration;
	private Date dateIssued;
	private CustomerDTO customerDTO;
}
