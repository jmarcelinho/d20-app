package com.example.d20.controllers;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import com.example.d20.message.request.LoginForm;
import com.example.d20.message.request.SignUpForm;
import com.example.d20.model.Role;
import com.example.d20.model.RoleName;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoginControllerTests {

	@Autowired
	LoginController loginController;
	
	// Checking if method "registerUser" return StatusCode 200 when perfom
	// a successfully account register
	// Make 3 accounts with valids parameters
	@Test
	public void registerAccountValid() {
		Set<String> role = new HashSet<>();
		role.add("admin");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso@abraba.com", "40028922", role, "finger");
		
		ResponseEntity response = loginController.registerUser(acc1);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		
		role = new HashSet<>();
		role.add("admin");
		SignUpForm acc2 = new SignUpForm("Admin", "dos Santos", "admin@admin.com", "40028922", role, "admin");
		
		response = loginController.registerUser(acc2);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		
		role = new HashSet<>();
		role.add("user");
		SignUpForm acc3 = new SignUpForm("User", "da Silva", "user@user.com", "40028922", role, "user");
		
		response = loginController.registerUser(acc3);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if the method return a "throw" ConstraintViolationException
	// when the variable Email it's wrong
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void registerAccountInvalidEmail() {
		Set<String> role = new HashSet<>();
		role.add("admin");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso.abrabacadabra", "40028922", role, "finger");
		
		loginController.registerUser(acc1);
	}
	
	// Checking if the method return a "throw" TransactionSystemException
	// when the variable Telephone it's less than allowed
	@Test(expected = TransactionSystemException.class)
	public void registerAccountInvalidTelephoneLess() {
		Set<String> role = new HashSet<>();
		role.add("admin");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso@abrabacadabra", "1234567", role, "finger");
		
		loginController.registerUser(acc1);
	}
	
	// Checking if the method return a "throw" TransactionSystemException
	// when the variable Telephone it's Greater than allowed
	@Test(expected = TransactionSystemException.class)
	public void registerAccountInvalidTelephoneGreater() {
		Set<String> role = new HashSet<>();
		role.add("admin");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso@abrabacadabra", "12345678910", role, "finger");
		
		loginController.registerUser(acc1);
	}
	
	// Checking if the method return a "throw" NullPointerException
	// when the variable Email it's NULL
	@Test(expected = NullPointerException.class)
	public void registerAccountInvalidPasswordNull() {
		Set<String> role = new HashSet<>();
		role.add("admin");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso@abrabacadabra", "123456789", role, null);
		
		ResponseEntity response = loginController.registerUser(acc1);
	}
	
	// Checking if the method return a "throw" ConstraintViolationException
	// when the variable Role it's different of "admin" and "user"
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void registerAccountInvalidRole() {
		Set<String> role = new HashSet<>();
		role.add("ademir");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso.abrabacadabra", "40028922", role, "finger");
		
		loginController.registerUser(acc1);
	}
	
	// Checking if method "authenticateUser" return StatusCode 200 when perfom
	// a successfully account login
	@Test
	public void loginAccountValid() {
		Set<String> role = new HashSet<>();
		role.add("ademir");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso@abrabacadabra.com", "40028922", role, "finger");
		
		loginController.registerUser(acc1);
		LoginForm login = new LoginForm("lanso@abrabacadabra.com", "finger");
		
		ResponseEntity response = loginController.authenticateUser(login);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if the method return a "throw" ConstraintViolationException
	// when the variable password it's wrong
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void loginAccountInvalidPassword() {
		Set<String> role = new HashSet<>();
		role.add("ademir");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso.abrabacadabra", "40028922", role, "finger");
		
		loginController.registerUser(acc1);
		LoginForm login = new LoginForm("lanso.abrabacadabra", "fingur");
		
		ResponseEntity response = loginController.authenticateUser(login);
	}
	
	// Checking if the method return a "throw" ConstraintViolationException
	// when the variable Email it's wrong
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void loginAccountInvalidEmail() {
		Set<String> role = new HashSet<>();
		role.add("ademir");
		SignUpForm acc1 = new SignUpForm("Lanso", "de Abraba", "lanso.abrabacadabra", "40028922", role, "finger");
		
		loginController.registerUser(acc1);
		LoginForm login = new LoginForm("lansei.abrabacadabra", "fingur");
		
		ResponseEntity response = loginController.authenticateUser(login);
	}
	
}
