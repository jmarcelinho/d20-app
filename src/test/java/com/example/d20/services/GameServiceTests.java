package com.example.d20.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameServiceTests {
	
	@Autowired
	private GameService gameService;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Assertions.assertThat(game.getName()).isEqualTo("Munchkin");
		
		User user = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		Assertions.assertThat(user.getEmail()).isEqualTo("aaa@gmail.com");
		
		Assertions.assertThat(gameService.getAllGames().size()).isEqualTo(0);
		gameService.addGame(game);
		Assertions.assertThat(gameService.getAllGames().size()).isEqualTo(1);
		
		Assertions.assertThat(gameService.getGameByGenre("RPG").get(0)).isEqualTo(game);
		
	}
	
	@Test
	public void deleteTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		
		gameService.addGame(game);
		Assertions.assertThat(gameService.getAllGames().size()).isEqualTo(1);
		Assertions.assertThat(gameService.getGameByName("Munchkin").size()).isEqualTo(1);
		
		gameService.delete(game.getId());
		
		Assertions.assertThat(gameService.getAllGames().size()).isEqualTo(0);
	}
	
	@Test
	public void updateTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		gameService.addGame(game);
		Assertions.assertThat( gameService.getGameByName("7Wonders").size() ).isEqualTo(0);
		Assertions.assertThat( gameService.getGameByName("Munchkin").size() ).isEqualTo(1);
		
		game.setName("7Wonders");
		game.setType("Cartas");
		
		gameService.updateGame(game.getId(), game);
		Assertions.assertThat( gameService.getGameByName("7Wonders").size() ).isEqualTo(1);
		Assertions.assertThat( gameService.getGameByName("Munchkin").size() ).isEqualTo(0);
		
	}
	
	@Test
	public void findAllByGenreTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("Yoooo", "Tabuleiro", "RPG");
		
		gameService.addGame(game);
		gameService.addGame(game2);
		
		Assertions.assertThat(gameService.getGameByGenre("RPG").size()).isEqualTo(2);
		Assertions.assertThat(gameService.getGameByGenre("RPG").get(0)).isEqualTo(game);
		Assertions.assertThat(gameService.getGameByGenre("RPG").get(1)).isEqualTo(game2);
	}
	
	@Test
	public void findAllByNameTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("Munchkin", "Cartas", "RPG");
		
		Assertions.assertThat(game).isNotEqualTo(game2);
		
		gameService.addGame(game);
		gameService.addGame(game2);
		
		Assertions.assertThat( gameService.getGameByName("Munchkin").size() ).isEqualTo(2);
		Assertions.assertThat( gameService.getGameByName("Munchkin").get(0) ).isEqualTo(game);
		Assertions.assertThat( gameService.getGameByName("Munchkin").get(1) ).isEqualTo(game2);
	}
	
	@Test
	public void findAllByTypeTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("Yoooo", "Tabuleiro", "RPG");
		Assertions.assertThat(game).isNotEqualTo(game2);
		gameService.addGame(game);
		gameService.addGame(game2);
		
		Assertions.assertThat( gameService.getGameByType("uhuu").size() ).isEqualTo(0);
		Assertions.assertThat( gameService.getGameByType("Tabuleiro").size() ).isEqualTo(2);
		Assertions.assertThat( gameService.getGameByType("Tabuleiro").get(0) ).isEqualTo(game);
		Assertions.assertThat( gameService.getGameByType("Tabuleiro").get(1) ).isEqualTo(game2);
		
	}
}
