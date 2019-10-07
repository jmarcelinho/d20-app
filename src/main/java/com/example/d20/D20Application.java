package com.example.d20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.d20.model.Role;
import com.example.d20.model.RoleName;
import com.example.d20.repository.RoleRepository;

@SpringBootApplication
public class D20Application implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(D20Application.class, args);
	}
	
	@Override
	public void run(String... params) throws Exception {
		//roleRepository.save(new Role(RoleName.ROLE_USER));
		//roleRepository.save(new Role(RoleName.ROLE_ADMIN));
	}


}
