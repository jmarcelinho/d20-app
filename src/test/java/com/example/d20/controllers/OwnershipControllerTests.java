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
public class OwnershipControllerTests {
	
	@Autowired
	OwnershipController ownershipController;
	
	@Autowired
	UserController userController;
	
	@Autowired
	GameController gameController;
	
	// Adding a ownership to valids to make tests
	@Test
	@Before
	public void addOwnershipValid() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		
		userController.add(owner);
		gameController.add(game);
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		ResponseEntity response = ownershipController.add(ownership);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	// Checking if 'getAll'return StatusCode 200
	@Test
	public void getAllOwnerships() {
		ResponseEntity response = ownershipController.getAll();
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
		
	// Checking if getOwnershipById return StatusCode 200 when exist a valid Id 
	@Test
	public void getOwnershipByIdValid() {
		ResponseEntity response = ownershipController.getOwnershipById(2);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if a 'throw' is returned when getOwnershipById receive a positive invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getOwnershipByIdInvalid1() {
		ownershipController.getOwnershipById(4);
	}
	
	// Checking if a 'throw' is returned when getOwnershipById receive a negative invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getOwnershipByIdInvalid2() {
		ownershipController.getOwnershipById(-2);
	}
	
	// Checking if 'erase' return StatusCode 204 when receive a valid ID
	@Test
	public void eraseValid() {
		ResponseEntity response = ownershipController.erase(2);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(204);
	}
	
	// Checking if 'erase' return StatusCode 404 when receive a invalid ID
	@Test(expected = NoSuchElementException.class)
	public void eraseInvalid() {
		ResponseEntity response = ownershipController.erase(5);
	}
}
