package com.example.d20.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.d20.model.Account;
import com.example.d20.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    PasswordEncoder encoder;
	
	public Account addAccount(Account account) {
		return this.accountRepository.save(account);
	}
	
	public Account getAccountByEmail(String email) {
		return this.accountRepository.findByEmail(email).get();
	}

	public Account changePassword(String email, String password) {
		Account acc = this.getAccountByEmail(email);
		acc.setPassword(encoder.encode(password));
		this.accountRepository.save(acc);
		return acc;
	}

	public boolean existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}
	
	public boolean verifyByAuth(Authentication authentication, String password) {
		Account acc = getAccountByEmail(authentication.getName());
		
		return encoder.matches(password, acc.getPassword());
	}
	
	public boolean setAccPassword(Authentication authentication, String password) {
		Account acc = getAccountByEmail(authentication.getName());
		if(acc == null) return false;
		
		this.changePassword(authentication.getName(), password);
		return true;
	}
}