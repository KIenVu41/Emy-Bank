package com.emybank.controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emybank.model.AccountDTO;
import com.emybank.model.LoanDTO;
import com.emybank.model.TransactionDTO;
import com.emybank.service.AccountService;
import com.emybank.service.LoanService;
import com.emybank.service.TransactionService;
import com.emybank.utils.DateUtils;

@Controller
@RequestMapping(value = "/member")
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value = "/account")
	public String init(Model model, HttpSession session) {	
		String username = getPrincipal();
		AccountDTO account = accountService.findByUsername(username);
		String mask = maskNumber(String.valueOf(account.getId()), "xxxxx###");
		String currency = formatCurrency(account.getBalance());
		model.addAttribute("amount", currency);
		model.addAttribute("mask", mask);
		session.setAttribute("account", account);	
		
		return "account";
	}
	
	@GetMapping(value = "/loan-calculator")
	public String loanCalculator() {
		return "loanCalculator";
	}
	
	@GetMapping(value = "/transfers")
	public String transfers(Model model, @RequestParam(value = "status", required = false) String status) {
		model.addAttribute("transaction", new TransactionDTO());
		return "transfers";
	}
	
	@PostMapping(value = "/transfers")
	public String transfersAccept(HttpSession session, Model model, @ModelAttribute(name="transaction") TransactionDTO transactionDTO) throws ParseException {
		Object obj = session.getAttribute("account");
		if(obj != null) {
			AccountDTO account = (AccountDTO) obj;
			if(account.getBalance() < transactionDTO.getAmount()) {
				return "redirect:/member/transfers?status=failed";
			}
			transactionDTO.setAccountDTO(account);
			LocalDate lt = LocalDate.now();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lt.toString());
			transactionDTO.setDateIssued(date);
			long amount = account.getBalance() - transactionDTO.getAmount();
			account.setBalance(amount);
			transactionService.add(transactionDTO);
			accountService.update(account);
			session.setAttribute("account", account);
		}
		return "redirect:/member/transfers?status=ok";
	}
	
	@GetMapping(value = "/loan")
	public String loan(Model model, @RequestParam(value = "status", required = false) String status) {
		model.addAttribute("loan", new LoanDTO());
		if(status != null) {
			DateUtils dateUtils = new DateUtils();
			LocalDate result = dateUtils.addDaysSkippingWeekends(LocalDate.now());
			String date = result.toString();
			model.addAttribute("date", date);
		}
		return "loan";
	}
	
	@PostMapping(value = "/loan")
	public String loanAccept(Model model, @ModelAttribute(name="loan") LoanDTO loanDTO, HttpSession session) throws ParseException {
		DateUtils dateUtils = new DateUtils();
		LocalDate result = dateUtils.addDaysSkippingWeekends(LocalDate.now());
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(result.toString());
		Object obj = session.getAttribute("account");
		if(obj != null) {
			AccountDTO account = (AccountDTO) obj;
			loanDTO.setDateIssued(date);
			loanDTO.setCustomerDTO(account.getCustomerDTO());
			loanService.add(loanDTO);
		}
		return "redirect:/member/loan?status=ok";
	}
	
	@GetMapping(value = "/report")
	public String report(Model model, HttpServletRequest req, @RequestParam(value = "from", required = false) String fromStr, @RequestParam(value = "to", required = false) String toStr, @RequestParam(value = "page", required = false) Integer page) throws ParseException {
		page = page == null ? 1 : page;
		Date fromDate = new Date();
		Date toDate = new Date();
		if(fromStr != null && toStr != null) {
			 fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromStr);
			 toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toStr);
		}else {
			 fromStr = "1970-01-01";
			 toStr = "2100-01-01";
			 fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(fromStr);
			 toDate = new SimpleDateFormat("yyyy-MM-dd").parse(toStr);
		}
		int count = fromStr.equals("1970-01-01") ? transactionService.countAll(): transactionService.countByDate(fromDate, toDate);
		int pageSize = 5;
		int endPage = 0;
		int offset = page * pageSize - pageSize;
		
		List<TransactionDTO> transactionDTOs = transactionService.findAll(fromDate, toDate, offset, pageSize);
	
		endPage = count / pageSize;
		
		if(count % pageSize != 0) {
			endPage += 1;
		}

		req.setAttribute("endPage", endPage);
		req.setAttribute("transactionList", transactionDTOs);
		req.setAttribute("current", page);
		req.setAttribute("from", fromStr);
		req.setAttribute("to", toStr);
		
		
		return "report";
	}

	
	public String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
	public String maskNumber(String number, String mask) {
		 
	      int index = 0;
	      StringBuilder masked = new StringBuilder();
	      for (int i = 0; i < mask.length(); i++) {
	         char c = mask.charAt(i);
	         if (c == '#') {
	            masked.append(number.charAt(index));
	            index++;
	         } else if (c == 'x') {
	            masked.append(c);
	            index++;
	         } else {
	            masked.append(c);
	         }
	      }
	      return masked.toString();
	   }
	
	public String formatCurrency(long amount) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String formated = currencyVN.format(amount);
		return formated;
	}
}
