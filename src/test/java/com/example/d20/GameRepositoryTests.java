package com.example.d20;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.repository.GameRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTests {
	@Autowired
	GameRepository gameRepository;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
	}
	
	// checking if findByCpf is working as intended
	@Test
	public void findByCpfTest() {
	}
	
	// checking if findByCpf is working as intended, by updating a user cpf
	// then searching for it again
	@Test
	public void findByCpfTest2() {
	}
	
	// checking if findByEmail is working as intended
	@Test
	public void findByEmailTest() {
	}
	
	// checking if findByEmail is working as intended, by updating a user email
	// then searching for it again
	@Test
	public void findByEmailTest2() {
	}
	
	// checking if findAllByFname is working as intended
	@Test
	public void findAllBy() {
	}
}
