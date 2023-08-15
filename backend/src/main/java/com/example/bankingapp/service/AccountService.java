package com.example.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapp.dao.AccountRepository;
import com.example.bankingapp.dao.AddressRepository;
import com.example.bankingapp.dao.CustomerRepository;
import com.example.bankingapp.dao.OccupationRepository;
import com.example.bankingapp.model.Account;
import com.example.bankingapp.model.AccountData;
import com.example.bankingapp.model.Address;
import com.example.bankingapp.model.Customer;
import com.example.bankingapp.model.Occupation;

@Service
public class AccountService {

	@Autowired
	AccountRepository accRepo;

	@Autowired
	CustomerRepository userRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	OccupationRepository occupationRepo;

	public String createAccount(AccountData accountData, String username) {

		try {
			Customer u = userRepo.findById(username).get();
			Account account = accountData.getAccount();
			Occupation occupation = accountData.getOccupation();
			List<Address> address = accountData.getAddress();

			account.setCustomer(u);
			occupation.setCustomer(u);
			for (Address a : address)
				a.setCustomer(u);

			occupationRepo.save(occupation);
			addressRepo.saveAll(address);
			accRepo.save(account);

			return "Account Created";
		} catch (Error e) {
			return "Error creating account: " + e.toString();
		}
	}

}
