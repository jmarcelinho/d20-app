package com.example.d20.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d20.model.Loan;
import com.example.d20.model.Ownership;
import com.example.d20.model.User;
import com.example.d20.repository.LoanRepository;

@Service
public class LoanService {
	@Autowired
	private LoanRepository loanRepository;
	
	public List<Loan> getAllLoans() {
		return this.loanRepository.findAll();
	}
	
	public Loan getLoanById(Integer id) {
		return this.loanRepository.findById(id).get();
	}
	
	public Loan addLoan(Loan loan) {
		return this.loanRepository.save(loan);
	}
	
	public boolean delete(Integer id) {
		Loan d_loan = this.getLoanById(id);
		
		if(d_loan != null) {
			this.loanRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public List<Loan> getLoanByItem(Ownership item) {
		return this.loanRepository.findAllByItem(item);
	}
	
	public List<Loan> getLoanByLoanee(User loanee) {
		return this.loanRepository.findAllByLoanee(loanee);
	}
}
