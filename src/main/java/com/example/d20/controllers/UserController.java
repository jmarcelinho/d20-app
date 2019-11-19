package com.example.d20.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.d20.message.request.EditForm;
import com.example.d20.model.User;
import com.example.d20.services.AccountService;
import com.example.d20.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = this.userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable Integer id){
		User user = this.userService.getUserById(id);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/name/{name}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<User> > getUserByName(@PathVariable String name) {
        List<User> fuser = userService.getUserByFname(name);
        List<User> luser = userService.getUserByLname(name);
        fuser.addAll(luser);
        return ResponseEntity.ok(fuser);
    }
	
	@PostMapping
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> add(@Valid @RequestBody User userBody){
		User user = this.userService.addUser(userBody);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.userService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/info")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public User getInfo(Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        return user;
    }
	
	
	@PostMapping("/edit/name")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<String> setFname(Authentication authentication,@Valid @RequestBody EditForm form) {
        if(accountService.verifyByAuth(authentication, form.getPassword())) {
        	if(userService.setUserName(authentication, form.getItem())) {
        		return new ResponseEntity<>("name successfully modified", HttpStatus.ACCEPTED);
        	}else {
        		return new ResponseEntity<>("name not modified", HttpStatus.PRECONDITION_FAILED);
        	}
        }
        
        return new ResponseEntity<>("Name not modified", HttpStatus.PRECONDITION_FAILED);
    }
	
	@PostMapping("/edit/telephone")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<String> setTelephone(Authentication authentication,@Valid @RequestBody EditForm form) {
        if(accountService.verifyByAuth(authentication, form.getPassword())) {
        	if(userService.setUserTel(authentication, form.getItem())) {
        		return new ResponseEntity<>("Telephone successfully modified", HttpStatus.ACCEPTED);
        	}else {
        		return new ResponseEntity<>("Telephone not modified", HttpStatus.PRECONDITION_FAILED);
        	}
        }
        
        return new ResponseEntity<>("Telephone not modified", HttpStatus.PRECONDITION_FAILED);
    }
	
	@PostMapping("/edit/password")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<String> setPassword(Authentication authentication,@Valid @RequestBody EditForm form) {
        if(accountService.verifyByAuth(authentication, form.getPassword())) {
        	if(accountService.setAccPassword(authentication, form.getItem())) {
        		return new ResponseEntity<>("Password successfully modified", HttpStatus.ACCEPTED);
        	}else {
        		return new ResponseEntity<>("Password not modified", HttpStatus.PRECONDITION_FAILED);
        	}
        }
        
        return new ResponseEntity<>("Password not modified", HttpStatus.PRECONDITION_FAILED);
    }
}
