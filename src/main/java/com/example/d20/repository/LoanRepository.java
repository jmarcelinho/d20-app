package com.example.d20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.d20.model.Loan;
import com.example.d20.model.Ownership;
import com.example.d20.model.User;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	List<Loan> findAllByItem(Ownership item);
	List<Loan> findAllByLoanee(User loanee);
	List<Loan> findAllByFinished(boolean finished);
}
