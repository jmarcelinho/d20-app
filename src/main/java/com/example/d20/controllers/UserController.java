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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.d20.model.User;
import com.example.d20.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAll() {
		return this.userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUsuarioById(@PathVariable Integer id){
		User user = this.userService.getUserById(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> add(@Valid @RequestBody User userBody){
		User user = this.userService.addUser(userBody);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id, @Valid @RequestBody User userBody){
		User user = this.userService.updateUser(id, userBody);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.userService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
