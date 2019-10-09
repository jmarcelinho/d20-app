package com.example.d20;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.d20.controllers.LoginController;
import com.example.d20.message.request.SignUpForm;
import com.example.d20.model.Role;
import com.example.d20.model.RoleName;
import com.example.d20.repository.RoleRepository;

@SpringBootApplication
public class D20Application implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	LoginController loginController;
	
	public static void main(String[] args) {
		SpringApplication.run(D20Application.class, args);
	}
	
	@Override
	public void run(String... params) throws Exception {
		//roleRepository.save(new Role(RoleName.ROLE_USER));
		roleRepository.save(new Role(RoleName.ROLE_ADMIN));
		
		SignUpForm adm = new SignUpForm(); 
		adm.setName("ADM");
		adm.setLastname("dos Santos");
		adm.setEmail("adm@adm.com");
		adm.setPassword("admin");
		Set<String> roles = new HashSet();
		roles.add("admin");
		adm.setRole(roles);
		loginController.registerUser(adm);
		
		SignUpForm user = new SignUpForm(); 
		user.setName("USER");
		user.setLastname("da Silva");
		user.setEmail("user@user.com");
		user.setPassword("user");
		loginController.registerUser(user);
	}


}
