package com.example.d20;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.User;
import com.example.d20.model.Game;
import com.example.d20.model.Ownership;
import com.example.d20.repository.OwnershipRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnershipRepositoryTests {
	@Autowired
	OwnershipRepository ownershipRepository;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		Assertions.assertThat(ownership.getPrice()).isEqualTo(15.5);
		
		Assertions.assertThat( ownershipRepository.findAll().size() ).isEqualTo(0);
		ownershipRepository.save(ownership);
		
		Assertions.assertThat( ownershipRepository.findAll().size() ).isEqualTo(1);
		Assertions.assertThat( ownershipRepository.findAll().get(0) ).isEqualTo(ownership);
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		ownershipRepository.save(ownership);
		Assertions.assertThat( ownershipRepository.findAll().size() ).isEqualTo(1);
		
		ownershipRepository.delete(ownership);
		Assertions.assertThat( ownershipRepository.findAll().size() ).isEqualTo(0);
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		ownershipRepository.save(ownership);
		
		Assertions.assertThat( ownershipRepository.findAllByAvailability(false).size() ).isEqualTo(0);
		Assertions.assertThat( ownershipRepository.findAllByAvailability(true).size() ).isEqualTo(1);
		
		ownership.setAvailability(false);
		ownership.setInfo("Testezao");
		ownershipRepository.save(ownership);
		
		Assertions.assertThat( ownershipRepository.findAllByAvailability(true).size() ).isEqualTo(0);
		Assertions.assertThat( ownershipRepository.findAllByAvailability(false).size() ).isEqualTo(1);
		
		Ownership updatedOwnership = ownershipRepository.findAllByAvailability(false).get(0);
		Assertions.assertThat(updatedOwnership).isEqualTo(game);
	}
	
	// checking if findAllByOwner is working as intended
	@Test
	public void findAllByOwnerTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		Game game2 = new Game("Coup", "Cartas", "RPG");
		Ownership ownership2 = new Ownership(owner, game2, 20, "Testezinho", false);
		
		ownershipRepository.save(ownership);
		ownershipRepository.save(ownership2);
		
		Assertions.assertThat( ownershipRepository.findAllByOwner(owner).size() ).isEqualTo(2);
		Assertions.assertThat( ownershipRepository.findAllByOwner(owner).get(0) ).isEqualTo(ownership);
		Assertions.assertThat( ownershipRepository.findAllByOwner(owner).get(1) ).isEqualTo(ownership2);
	}
	
	// checking if findAllByAvailability is working as intended
	@Test
	public void findAllByAvailabilityTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		Game game2 = new Game("Coup", "Cartas", "RPG");
		Ownership ownership2 = new Ownership(owner, game2, 20, "Testezinho", false);
		
		Game game3 = new Game("Uaaaa", "Cartas", "TT");
		Ownership ownership3 = new Ownership(owner, game3, 10, "Testezozer", false);
		
		ownershipRepository.save(ownership);
		ownershipRepository.save(ownership2);
		ownershipRepository.save(ownership3);
		
		Assertions.assertThat( ownershipRepository.findAllByAvailability(true).size() ).isEqualTo(1);
		Assertions.assertThat( ownershipRepository.findAllByAvailability(false).size() ).isEqualTo(2);
		
		Assertions.assertThat( ownershipRepository.findAllByAvailability(false).get(0) ).isEqualTo(ownership2);
		Assertions.assertThat( ownershipRepository.findAllByAvailability(false).get(1) ).isEqualTo(ownership3);
	}
}
