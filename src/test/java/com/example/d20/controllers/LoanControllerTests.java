package com.example.d20.controllers;


import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.model.Loan;
import com.example.d20.model.Ownership;
import com.example.d20.model.User;
import com.example.d20.repository.OwnershipRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanControllerTests {
	
	@Autowired
	LoanController loanController;
	
	@Autowired
	UserController userController;
	
	@Autowired
	GameController gameController;
	
	@Autowired
	OwnershipRepository ownershipRepository;
	
	// Adding a Loan to valids to make tests
	@Test
	@Before
	public void addLoanValid() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "12345678", "pigpig@oink.com");
		userController.add(owner);
		userController.add(loanee);
		gameController.add(game);
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		ResponseEntity response = loanController.add(loan);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if 'getAll'return StatusCode 200
	@Test
	public void getAllGames() {
		ResponseEntity response = loanController.getAll();
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
		
	// Checking if getLoanById return StatusCode 200 when exist a valid Id 
	@Test
	public void getLoanByIdValid() {
		ResponseEntity response = loanController.getLoanById(3);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if a 'throw' is returned when getLoanById receive a positive invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getLoanByIdInvalid1() {
		loanController.getLoanById(4);
	}
	
	// Checking if a 'throw' is returned when getLoanById receive a negative invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getLoanByIdInvalid2() {
		loanController.getLoanById(-2);
	}
	
	// Checking if 'erase' return StatusCode 204 when receive a valid ID
	@Test
	public void eraseValid() {
		ResponseEntity response = loanController.erase(3);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(204);
	}
	
	// Checking if 'erase' return StatusCode 404 when receive a invalid ID
	@Test(expected = NoSuchElementException.class)
	public void eraseInvalid() {
		ResponseEntity response = loanController.erase(5);
	}
}
