package com.example.d20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.d20.model.Role;
import com.example.d20.model.RoleName;
import com.example.d20.repository.RoleRepository;

@SpringBootApplication
public class D20Application {
	
	static RoleRepository roleRepository;
	
	public static void main(String[] args) {
		roleRepository.save(new Role(RoleName.ROLE_USER));
		roleRepository.save(new Role(RoleName.ROLE_ADMIN));
		roleRepository.save(new Role(RoleName.ROLE_PM));
		
		SpringApplication.run(D20Application.class, args);
	}

}
