package com.example.d20.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.User;
import com.example.d20.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@AutoConfigureTestDatabase
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Assertions.assertThat(userService.getAllUsers().size()).isEqualTo(0);
		userService.addUser(user);
		
		Assertions.assertThat(userService.getAllUsers().size()).isEqualTo(1);
		
		Assertions.assertThat(userService.getUserByFname("Matheus").size()).isEqualTo(1);
	}

	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		userService.addUser(user);
		
		Assertions.assertThat(userService.getAllUsers().size()).isEqualTo(1);
		Assertions.assertThat(userService.getUserByFname("Matheus").size()).isEqualTo(1);
		
		userService.delete(user.getEmail());
		
		Assertions.assertThat(userService.getAllUsers().size()).isEqualTo(0);
		
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		userService.addUser(user);
		
		Assertions.assertThat(userService.getUserByFname("Theusao").size()).isEqualTo(0);
		Assertions.assertThat(userService.getUserByFname("Matheus").size()).isEqualTo(1);
		
		user.setFname("Theusao");
		user.setTelephone("12131212");
		
		userService.updateUser(user.getEmail(), user);
		
		Assertions.assertThat(userService.getUserByFname("Theusao").size()).isEqualTo(1);
		Assertions.assertThat(userService.getUserByFname("Matheus").size()).isEqualTo(0);
		
	}
	
	// checking if findByEmail is working as intended
	@Test
	public void findByEmailTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		userService.addUser(user);
		
		Assertions.assertThat(userService.getUserByEmail("uaaa@gmail.com")).isNull();
		
		Assertions.assertThat(userService.getUserByEmail("aaa@gmail.com")).isEqualTo(user);
		
	}
	
	// checking if findAllByFname is working as intended
	@Test
	public void findAllByFNameTest() {
		User user = new User("Matheus", "Oliveira", "1234", "12131212", "aaa@gmail.com");
		User user2 = new User("Matheus", "Dae", "4321", "64532325", "bbb@gmail.com");
		
		userService.addUser(user);
		userService.addUser(user2);
		
		Assertions.assertThat( userService.getUserByFname("Matheus").get(0) ).isEqualTo(user);
		Assertions.assertThat( userService.getUserByFname("Matheus").get(1) ).isEqualTo(user2);
	}
	
}
