package com.emybank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	private long id;
	private long balance;
	private boolean status;
	private String type;
	private String username;
	private String password;
	private String currency;
	private String role;
	private CustomerDTO customerDTO;
	
	public boolean getStatus() {
		return status;
	}
}
