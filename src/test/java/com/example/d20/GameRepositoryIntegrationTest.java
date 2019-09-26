package com.example.d20;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.repository.GameRepository;
import com.example.d20.services.GameService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryIntegrationTest {
	@Autowired
    private GameService gameService;
 
    @Autowired
    private GameRepository gameRepository;
    
    @Test
    public void test0FindAllByName() {
    	Game test = new Game("Munchkin", "Tabuleiro", "RPG");
    	gameService.addGame(test);
 
    	List<Game> found = gameRepository.findAllByName(test.getName());
    	assertEquals(found.get(0).getName(), test.getName() );
    }
}
