package com.example.d20.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.model.Ownership;
import com.example.d20.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OwnershipServiceTests {
	
	@Autowired
	OwnershipService ownershipService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	GameService gameService;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		
		userService.addUser(owner);
		gameService.addGame(game);
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		Assertions.assertThat(ownershipService.getAllOwnerships().size()).isEqualTo(0);
		
		ownershipService.addOwnership(ownership);
		
		Assertions.assertThat(ownershipService.getAllOwnerships().size()).isEqualTo(1);
		
		Assertions.assertThat(ownershipService.getAllOwnerships().get(0)).isEqualTo(ownership);
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userService.addUser(owner);
		gameService.addGame(game);
		
		ownershipService.addOwnership(ownership);
		Assertions.assertThat( ownershipService.getAllOwnerships().size() ).isEqualTo(1);
		
		ownershipService.delete(ownership.getId());
		Assertions.assertThat( ownershipService.getAllOwnerships().size() ).isEqualTo(0);
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userService.addUser(owner);
		gameService.addGame(game);
		
		ownershipService.addOwnership(ownership);
		
		Assertions.assertThat( ownershipService.getOwnershipByAvailability(false).size() ).isEqualTo(0);
		Assertions.assertThat( ownershipService.getOwnershipByAvailability(true).size() ).isEqualTo(1);
		
		ownership.setAvailability(false);
		ownership.setInfo("Testezao");
		
		ownershipService.updateOwnership(ownership.getId(), ownership);
		Assertions.assertThat( ownershipService.getOwnershipByAvailability(true).size() ).isEqualTo(0);
		Assertions.assertThat( ownershipService.getOwnershipByAvailability(false).size() ).isEqualTo(1);
		
	}
	
	// checking if findAllByOwner is working as intended
		@Test
		public void findAllByOwnerTest() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			
			Game game2 = new Game("Coup", "Cartas", "RPG");
			Ownership ownership2 = new Ownership(owner, game2, 20, "Testezinho", false);
			
			userService.addUser(owner);
			gameService.addGame(game);
			gameService.addGame(game2);
			
			ownershipService.addOwnership(ownership);
			ownershipService.addOwnership(ownership2);
			
			
			Assertions.assertThat( ownershipService.getOwnershipByOwner(owner).size() ).isEqualTo(2);
			Assertions.assertThat( ownershipService.getOwnershipByOwner(owner).get(0) ).isEqualTo(ownership);
			Assertions.assertThat( ownershipService.getOwnershipByOwner(owner).get(1) ).isEqualTo(ownership2);
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
			
			userService.addUser(owner);
			gameService.addGame(game);
			gameService.addGame(game2);
			gameService.addGame(game3);
			
			ownershipService.addOwnership(ownership);
			ownershipService.addOwnership(ownership2);
			ownershipService.addOwnership(ownership3);
			
			Assertions.assertThat( ownershipService.getOwnershipByAvailability(true).size() ).isEqualTo(1);
			Assertions.assertThat( ownershipService.getOwnershipByAvailability(false).size() ).isEqualTo(2);
			
			Assertions.assertThat( ownershipService.getOwnershipByAvailability(false).get(0) ).isEqualTo(ownership2);
			Assertions.assertThat( ownershipService.getOwnershipByAvailability(false).get(1) ).isEqualTo(ownership3);
		}
}
