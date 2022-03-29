package com.emybank.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	@GetMapping(value = "/login")
	public String login(HttpServletRequest req, @RequestParam(name = "e", required = false) String error,@RequestParam(name = "logout", required = false) String logout) {
		if (error != null) {
			req.setAttribute("e", error);
		}
		if (logout != null) {
			req.setAttribute("logout", logout);
		}
		return "index";
	}
	
//	@PostMapping(value = "/login")
//	public String login2(HttpServletRequest req, @RequestParam(name = "e", required = false) String error,@RequestParam(name = "logout", required = false) String logout) {
//		if (error != null) {
//			req.setAttribute("e", error);
//		}
//		if (logout != null) {
//			req.setAttribute("logout", logout);
//		}
//		return "account";
//	}
}
