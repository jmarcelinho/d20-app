package com.example.d20.controllers;


import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import com.example.d20.model.Game;
import com.example.d20.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GameControllerTests {
	
	@Autowired
	GameController gameController;
	
	// Adding 3 games to valids to make tests
	@Test
	@Before
	public void addGameValid() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		ResponseEntity response = gameController.add(game);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		
		game = new Game("7 Wonders", "Cartas", "RPG");
		response = gameController.add(game);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		
		game = new Game("Monopoly", "Cartas", "RPG");
		response = gameController.add(game);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		
	}
	
	// Checking if 'getAll'return StatusCode 200
	@Test
	public void getAllGames() {
		ResponseEntity response = gameController.getAll();
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
		
	// Checking if getGameById return StatusCode 200 when exist a valid Id 
	@Test
	public void getGameByIdValid() {
		ResponseEntity response = gameController.getGameById(1);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
	}
	
	// Checking if a 'throw' is returned when getGameById receive a positive invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getGameByIdInvalid1() {
		gameController.getGameById(4);
	}
	
	// Checking if a 'throw' is returned when getGameById receive a negative invalid Id
	@Test(expected = NoSuchElementException.class)
	public void getGameByIdInvalid2() {
		gameController.getGameById(-2);
	}
	
	// Checking if getUserByName return StatusCode 200 when exist a valid Name
	// and the list of game's with this name
	@Test
	public void getGameByNameValid() {
		ResponseEntity response = gameController.getGameByName("Monopoly");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<Game> games = (List<Game>) response.getBody();
		Assertions.assertThat(games.size()).isGreaterThan(0);
	}
	
	// Checking if getGameByName return StatusCode 200 when exist a invalid Name
	// but return a empty list of game's
	@Test
	public void getGameByNameInvalid() {
		ResponseEntity response = gameController.getGameByName("Monogamia");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<Game> games = (List<Game>) response.getBody();
		Assertions.assertThat(games.size()).isEqualTo(0);
	}
	
	// Checking if getGameByGenre return StatusCode 200 when exist a valid Genre
	// and the list of game's with this genre
	@Test
	public void getGameByGenreValid() {
		ResponseEntity response = gameController.getGameByGenre("RPG");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<Game> games = (List<Game>) response.getBody();
		Assertions.assertThat(games.size()).isGreaterThan(0);
	}
	
	// Checking if getGameByName return StatusCode 200 when exist a invalid Genre
	// but return a empty list of game's
	@Test
	public void getGameByGenreInvalid() {
		ResponseEntity response = gameController.getGameByGenre("GRP");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<Game> games = (List<Game>) response.getBody();
		Assertions.assertThat(games.size()).isEqualTo(0);
	}
	
	// Checking if getGameByType return StatusCode 200 when exist a valid Type
	// and the list of game's with this type
	@Test
	public void getGameByTypeValid() {
		ResponseEntity response = gameController.getGameByType("Tabuleiro");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<Game> games = (List<Game>) response.getBody();
		Assertions.assertThat(games.size()).isGreaterThan(0);
	}
	
	// Checking if getGameByType return StatusCode 200 when exist a invalid Type
	// but return a empty list of game's
	@Test
	public void getGameByTypeInvalid() {
		ResponseEntity response = gameController.getGameByType("Janta");
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(200);
		List<Game> users = (List<Game>) response.getBody();
		Assertions.assertThat(users.size()).isEqualTo(0);
	}
		
	// Checking if 'erase' return StatusCode 204 when receive a valid ID
	@Test
	public void eraseValid() {
		ResponseEntity response = gameController.erase(3);
		Assertions.assertThat(response.getStatusCodeValue() ).isEqualTo(204);
	}
	
	// Checking if 'erase' return StatusCode 404 when receive a invalid ID
	@Test(expected = NoSuchElementException.class)
	public void eraseInvalid() {
		ResponseEntity response = gameController.erase(5);
	}
}
