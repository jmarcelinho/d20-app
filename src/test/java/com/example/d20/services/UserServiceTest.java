package com.example.d20.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test
	public void persistenceTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		
		Assertions.assertThat(userService.addUser(user)).isNotNull();
	}
}
