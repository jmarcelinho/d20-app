package com.example.d20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.d20.model.Role;
import com.example.d20.model.RoleName;
import com.example.d20.model.User;
import com.example.d20.repository.RoleRepository;
import com.example.d20.repository.UserRepository;

@SpringBootApplication
public class D20Application implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(D20Application.class, args);
	}
	
	@Override
	public void run(String... params) throws Exception {
		roleRepository.save(new Role(RoleName.ROLE_USER));
		roleRepository.save(new Role(RoleName.ROLE_ADMIN));
		
		User adm = new User("ADM", "dos Santos", "adm@adm.com", encoder.encode("admin"));
		userRepository.save(adm);
		User user = new User("USER", "da Silva", "user@user.com", encoder.encode("user"));
		userRepository.save(user);
	}


}
