package com.example.d20.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Account;
import com.example.d20.model.User;
import com.example.d20.repository.AccountRepository;
import com.example.d20.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {
	@Autowired
	AccountRepository accountRepository;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		Account acc = new Account("aaa@gmail.com", "aaaa");
		Assertions.assertThat(acc.getEmail()).isEqualTo("aaa@gmail.com");
		
		Assertions.assertThat( accountRepository.findAll().size() ).isEqualTo(0);
		accountRepository.save(acc);
		Assertions.assertThat( accountRepository.findAll().size() ).isEqualTo(1);
		
		Optional<Account> retrievedUser = accountRepository.findByEmail("aaa@gmail.com");
		Assertions.assertThat( retrievedUser ).isNotEqualTo(Optional.empty());
		Assertions.assertThat( retrievedUser.get() ).isEqualTo(acc);
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		Account acc = new Account("aaa@gmail.com", "aaaa");
		Assertions.assertThat(acc.getEmail()).isEqualTo("aaa@gmail.com");
		
		accountRepository.save(acc);
		Assertions.assertThat( accountRepository.findAll().size() ).isEqualTo(1);
		
		accountRepository.delete(acc);
		Assertions.assertThat( accountRepository.findAll().size() ).isEqualTo(0);
	}
	
	// checking if findByEmail is working as intended
	@Test
	public void findByEmailTest() {
		Account acc = new Account("aaa@gmail.com", "aaaa");
		Assertions.assertThat(acc.getEmail()).isEqualTo("aaa@gmail.com");
		accountRepository.save(acc);
		
		Assertions.assertThat( accountRepository.findByEmail("uaaa@gmail.com") ).isEqualTo(Optional.empty());
		
		Optional<Account> retrievedUser = accountRepository.findByEmail("aaa@gmail.com");
		Assertions.assertThat( retrievedUser ).isNotEqualTo(Optional.empty());
		Assertions.assertThat( retrievedUser.get() ).isEqualTo(acc);
	}	
	
	// Checking if password has been changed successfully  
	@Test
	public void changePassword() {
		Account acc = new Account("aaa@gmail.com", "aaaa");
		Assertions.assertThat(acc.getEmail()).isEqualTo("aaa@gmail.com");
		accountRepository.save(acc);
		
		Optional<Account> retrievedUser = accountRepository.findByEmail("aaa@gmail.com");
		Assertions.assertThat( retrievedUser ).isNotEqualTo(Optional.empty());
		Assertions.assertThat( retrievedUser.get() ).isEqualTo(acc);
		
		acc.setPassword("bbbb");
		accountRepository.save(acc);
		
		retrievedUser = accountRepository.findByEmail("aaa@gmail.com");
		Assertions.assertThat( retrievedUser ).isNotEqualTo(Optional.empty());
		Assertions.assertThat( retrievedUser.get() ).isEqualTo(acc);
	}
}
