package com.emybank.model;


import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private long id;
	private String lastName;
	private String firstName;
	private String city;
	private String email;
	@Min(value = 11, message = "Your phone number must be at least 11 digits?")
	private String mobile;
	private String address;
}
