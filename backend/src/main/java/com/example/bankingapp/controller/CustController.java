package com.example.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapp.model.AccountBalance;
import com.example.bankingapp.model.Customer;
import com.example.bankingapp.model.LoginModel;
import com.example.bankingapp.service.CustService;

@RestController
@CrossOrigin("*")
public class CustController {
	@Autowired
	CustService custService;
	@PostMapping("/saveCustomer")
	public String saveCustomer(@RequestBody Customer cust) {
		String c=custService.saveCustomer(cust);
		return c;
	}
	
	@PostMapping("/checkLogin")
	public String validateUser(@RequestBody LoginModel u) {
		return custService.validateUser(u); 
	}
	
	@GetMapping("/fetchAccounts/{custId}")
	public List<String>fetchAccounts(@PathVariable("custId") String uname){
		System.out.println(uname);
		List<String>accountList=custService.fetchAccounts(uname);
		return accountList;
	}
	
	@GetMapping("/checkBalance/{custId}")
	public List<AccountBalance> checkBalance(@PathVariable("custId") String uname) {
		return custService.checkBalance(uname);
	}
}


