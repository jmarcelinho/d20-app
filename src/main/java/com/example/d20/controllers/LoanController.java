package com.example.d20.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Loan>> getAll() {
		List<Loan> loans = this.loanService.getAllLoans();
		return ResponseEntity.ok(loans);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Loan> getLoanById(@PathVariable Integer id) {
		Loan loan = this.loanService.getLoanById(id);
		
		if(loan == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(loan);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Loan> add(@Valid @RequestBody Loan userBody) {
		// TODO: if loan.item.availability is false, throw an error
		
		Loan loan = this.loanService.addLoan(userBody);
		return ResponseEntity.ok(loan);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Loan> finish(@PathVariable Integer id, @Valid @RequestBody Loan loanBody){
		Loan loan = this.loanService.finishLoan(id, loanBody);
		if(loan == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(loan);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.loanService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}

}
