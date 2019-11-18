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
import org.springframework.transaction.TransactionSystemException;

import com.example.d20.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTests {
	
	@Autowired
	UserController userController;
	
	// Adding 3 Users to valids to make tests
	@Test
	@Before
	public void addUserValid() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		ResponseEntity response = userController.add(user);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		user = new User("Amandio", "Ferreira", "12131212", "amanaio@gmail.com");
		response = userController.add(user);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		user = new User("Doug", "Watson", "12131212", "doug@watson.com");
		response = userController.add(user);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if a 'throw' is returned when email have a wrong format
	@Test(expected = TransactionSystemException.class)
	public void tryAddUserWithWrongEmailFormat() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa.com");
		userController.add(user);
	}
	
	// Checking if a 'throw' is returned when telephone have a size less than allowed
	@Test(expected = TransactionSystemException.class)
	public void tryAddUserWithWrongTelephoneFormat1() {
		User user = new User("Matheus", "Oliveira", "1234567", "aaa@gmail.com");
		userController.add(user);
	}
	
	// Checking if a 'throw' is returned when telephone have a size greater than allowed
	@Test(expected = TransactionSystemException.class)
	public void tryAddUserWithWrongTelephoneFormat2() {
		User user = new User("Matheus", "Oliveira", "12345678910", "aaa@gmail.com");
		userController.add(user);
	}
	
	// Checking if getUserById return StatusCode 200 when exist a valid Id 
	@Test
	public void getUserByIdValid() {
		ResponseEntity response = userController.getUserById(1);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if a 'throw' is returned when getUserById receive a positive invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getUserByIdInvalid1() {
		userController.getUserById(4);
	}
	
	// Checking if a 'throw' is returned when getUserById receive a negative invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getUserByIdInvalid2() {
		userController.getUserById(-2);
	}
	
	// Checking if getUserById return StatusCode 200 when exist a valid Name
	// and the list of user's with this name
	@Test
	public void getUserByNameValid() {
		ResponseEntity response = userController.getUserByName("Amandio");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<User> users = (List<User>) response.getBody();
		Assertions.assertThat(users.size()).isGreaterThan(0);
	}
	
	// Checking if getUserById return StatusCode 200 when exist a invalid Name
	// but return a empty list of User's
	@Test
	public void getUserByFnameInvalid() {
		ResponseEntity response = userController.getUserByName("Jose");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<User> users = (List<User>) response.getBody();
		Assertions.assertThat(users.size()).isEqualTo(0);
	}
	
	// Checking if 'erase' return StatusCode 204 when receive a valid ID
	@Test
	public void eraseValid() {
		ResponseEntity response = userController.erase(3);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(204);
	}
	
	// Checking if 'erase' return StatusCode 404 when receive a invalid ID
	@Test(expected = NoSuchElementException.class)
	public void eraseInvalid() {
		ResponseEntity response = userController.erase(5);
	}
}
