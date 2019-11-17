package com.example.d20;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.model.User;
import com.example.d20.repository.GameRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTests {
	@Autowired
	GameRepository gameRepository;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Assertions.assertThat(game.getName()).isEqualTo("Munchkin");
		
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Assertions.assertThat(user.getEmail()).isEqualTo("aaa@gmail.com");
		
		Assertions.assertThat( gameRepository.findAll().size() ).isEqualTo(0);
		gameRepository.save(game);
		
		Assertions.assertThat( gameRepository.findAll().size() ).isEqualTo(1);
		Assertions.assertThat( gameRepository.findAllByGenre("RPG").get(0) ).isEqualTo(game);
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		
		gameRepository.save(game);
		Assertions.assertThat( gameRepository.findAll().size() ).isEqualTo(1);
		Assertions.assertThat( gameRepository.findAllByName("Munchkin").size() ).isEqualTo(1);
		
		gameRepository.delete(game);
		Assertions.assertThat( gameRepository.findAll().size() ).isEqualTo(0);
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		gameRepository.save(game);
		
		Assertions.assertThat( gameRepository.findAllByName("7Wonders").size() ).isEqualTo(0);
		Assertions.assertThat( gameRepository.findAllByName("Munchkin").size() ).isEqualTo(1);
		
		game.setName("7Wonders");
		game.setType("Cartas");
		gameRepository.save(game);
		
		Assertions.assertThat( gameRepository.findAllByName("Munchkin").size() ).isEqualTo(0);
		Assertions.assertThat( gameRepository.findAllByName("7Wonders").size() ).isEqualTo(1);
		
		Game updatedGame = gameRepository.findAllByName("7Wonders").get(0);
		Assertions.assertThat( updatedGame ).isEqualTo(game);
	}
	
	// checking if findAllByGenre is working as intended
	@Test
	public void findAllByGenreTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("Yoooo", "Tabuleiro", "RPG");
		
		gameRepository.save(game);
		gameRepository.save(game2);
		
		Assertions.assertThat( gameRepository.findAllByGenre("RPG").size() ).isEqualTo(2);
		Assertions.assertThat( gameRepository.findAllByGenre("RPG").get(0) ).isEqualTo(game);
		Assertions.assertThat( gameRepository.findAllByGenre("RPG").get(1) ).isEqualTo(game2);
	}
	
	// checking if findAllByName is working as intended
	@Test
	public void findAllByNameTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("Munchkin", "Cartas", "RPG");
		Assertions.assertThat(game).isNotEqualTo(game2);
		
		gameRepository.save(game);
		gameRepository.save(game2);
		
		Assertions.assertThat( gameRepository.findAllByName("Munchkin").size() ).isEqualTo(2);
		Assertions.assertThat( gameRepository.findAllByName("Munchkin").get(0) ).isEqualTo(game);
		Assertions.assertThat( gameRepository.findAllByName("Munchkin").get(1) ).isEqualTo(game2);
	}
	
	// checking if findAllByType is working as intended
	@Test
	public void findAllByTypeTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("Yoooo", "Tabuleiro", "RPG");
		Assertions.assertThat(game).isNotEqualTo(game2);
		
		gameRepository.save(game);
		gameRepository.save(game2);
		
		Assertions.assertThat( gameRepository.findAllByType("zerado").size() ).isEqualTo(0);
		Assertions.assertThat( gameRepository.findAllByType("Tabuleiro").size() ).isEqualTo(2);
		Assertions.assertThat( gameRepository.findAllByType("Tabuleiro").get(0) ).isEqualTo(game);
		Assertions.assertThat( gameRepository.findAllByType("Tabuleiro").get(1) ).isEqualTo(game2);
	}
}
