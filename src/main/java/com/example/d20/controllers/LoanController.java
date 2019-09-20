package com.example.d20.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.d20.model.Loan;
import com.example.d20.services.LoanService;

@RestController
@CrossOrigin
@RequestMapping("/loan")
public class LoanController {
	@Autowired
	private LoanService loanService;
	
	@GetMapping
	public List<Loan> getAll() {
		return this.loanService.getAllLoans();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Loan> getLoanById(@PathVariable Integer id){
		Loan user = this.loanService.getLoanById(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<Loan> add(@Valid @RequestBody Loan userBody){
		Loan user = this.loanService.addLoan(userBody);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.loanService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}

}
