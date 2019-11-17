package com.example.d20;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.User;
import com.example.d20.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
	@Autowired
	UserRepository userRepository;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Assertions.assertThat(user.getEmail()).isEqualTo("aaa@gmail.com");
		
		Assertions.assertThat( userRepository.findAll().size() ).isEqualTo(0);
		userRepository.save(user);
		Assertions.assertThat( userRepository.findAll().size() ).isEqualTo(1);
		Assertions.assertThat( userRepository.findAllByFname("Matheus").size() ).isEqualTo(1);
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		
		userRepository.save(user);
		Assertions.assertThat( userRepository.findAll().size() ).isEqualTo(1);
		Assertions.assertThat( userRepository.findAllByFname("Matheus").size() ).isEqualTo(1);
		
		userRepository.delete(user);
		Assertions.assertThat( userRepository.findAll().size() ).isEqualTo(0);
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		userRepository.save(user);
		
		Assertions.assertThat( userRepository.findAllByFname("Theusao").size() ).isEqualTo(0);
		Assertions.assertThat( userRepository.findAllByFname("Matheus").size() ).isEqualTo(1);
		
		user.setFname("Theusao");
		user.setTelephone("332211");
		userRepository.save(user);
		
		Assertions.assertThat( userRepository.findAllByFname("Matheus").size() ).isEqualTo(0);
		Assertions.assertThat( userRepository.findAllByFname("Theusao").size() ).isEqualTo(1);
		
		User updatedUser = userRepository.getOne(user.getId());
		Assertions.assertThat( updatedUser.getTelephone() ).isEqualTo("332211");
	}
	
	// checking if findByCpf is working as intended
	@Test
	public void findByCpfTest() {
		User user = new User("Matheus", "Oliveira", "1234", "12131212", "aaa@gmail.com");
		userRepository.save(user);
		
		Assertions.assertThat( userRepository.findByCpf("1111") ).isEqualTo(null);
		
		User retrievedUser = userRepository.findByCpf("1234");
		Assertions.assertThat( retrievedUser ).isEqualTo(user);
	}
	
	// checking if findByCpf is working as intended, by updating a user cpf
	// then searching for it again
	@Test
	public void findByCpfTest2() {
		User user = new User("Matheus", "Oliveira", "1234", "12131212", "aaa@gmail.com");
		userRepository.save(user);
		
		Assertions.assertThat( userRepository.findByCpf("1234") ).isEqualTo(user);
		user.setCpf("4321");
		Assertions.assertThat( userRepository.findByCpf("1234") ).isEqualTo(null);
		
		User retrievedUser = userRepository.findByCpf("4321");
		Assertions.assertThat( retrievedUser ).isEqualTo(user);
	}
	
	// checking if findByEmail is working as intended
	@Test
	public void findByEmailTest() {
		User user = new User("Matheus", "Oliveira", "1234", "12131212", "aaa@gmail.com");
		userRepository.save(user);
		
		Assertions.assertThat( userRepository.findByEmail("uaaa@gmail.com") ).isEqualTo(null);
		
		User retrievedUser = userRepository.findByEmail("aaa@gmail.com");
		Assertions.assertThat( retrievedUser ).isEqualTo(user);
	}
	
	// checking if findByEmail is working as intended, by updating a user email
	// then searching for it again
	@Test
	public void findByEmailTest2() {
		User user = new User("Matheus", "Oliveira", "1234", "12131212", "aaa@gmail.com");
		userRepository.save(user);
		
		Assertions.assertThat( userRepository.findByEmail("aaa@gmail.com") ).isEqualTo(user);
		user.setEmail("dae@gmail.com");
		Assertions.assertThat( userRepository.findByEmail("aaa@gmail.com") ).isEqualTo(null);
		
		User retrievedUser = userRepository.findByEmail("dae@gmail.com");
		Assertions.assertThat( retrievedUser ).isEqualTo(user);
	}
	
	// checking if findAllByFname is working as intended
	@Test
	public void findAllByFnameTest() {
		User user = new User("Matheus", "Oliveira", "1234", "12131212", "aaa@gmail.com");
		User user2 = new User("Matheus", "Dae", "4321", "645323", "bbb@gmail.com");
		
		userRepository.save(user);
		userRepository.save(user2);
		
		Assertions.assertThat( userRepository.findAllByFname("Matheus").get(0) ).isEqualTo(user);
		Assertions.assertThat( userRepository.findAllByFname("Matheus").get(1) ).isEqualTo(user2);
	}
}
