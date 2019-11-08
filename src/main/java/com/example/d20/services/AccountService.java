package com.example.d20.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d20.model.Account;
import com.example.d20.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	
	public Account addAccount(Account account) {
		return this.accountRepository.save(account);
	}
	
	public Account getAccountByEmail(String email) {
		return this.accountRepository.findByEmail(email).get();
	}

	public Account changePassword(String email, String password) {
		Account newAcc = this.getAccountByEmail(email);
		
		if(newAcc != null) {
			accountRepository.delete(newAcc);
			newAcc.setPassword(password);
			this.accountRepository.save(newAcc);
		}
		return newAcc;
	}

	public boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
	
}