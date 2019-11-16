package com.example.d20.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d20.model.BankAccount;
import com.example.d20.repository.BankAccountRepository;

@Service
public class BankAccountService {
	
	@Autowired
	private BankAccountRepository bankRepository;
	
	public BankAccount addBankAccount(BankAccount bankAccount) {
		return this.bankRepository.save(bankAccount);
	}
	
	public BankAccount getBankAccountByEmail(String email) {
		return this.bankRepository.findByEmail(email).get();
	}
	
	public boolean setBankAccount(String email, String number, String branch) {
		BankAccount bankAccount = getBankAccountByEmail(email);
		
		if(bankAccount == null) return false;
		
		bankAccount.setBranch(branch);
		bankAccount.setNumber(number);
		this.bankRepository.save(bankAccount);
		
		return true;
	}
}
