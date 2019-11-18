package com.example.d20.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTests {
	
	@Autowired
	private GameService gameService;
	
	@Test
	public void persistenceTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Assertions.assertThat(game.getName()).isEqualTo("Munchkin");
		
		Assertions.assertThat(gameService.addGame(game)).isNotNull();
	}
}
