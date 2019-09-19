package com.example.d20.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d20.model.Game;
import com.example.d20.repository.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> getAllGames() {
		return this.gameRepository.findAll();
	}
	
	public Game getGameById(Integer id) {
		return this.gameRepository.findById(id).get();
	}
	
	public Game addGame(Game game) {
		return this.gameRepository.save(game);
	}
	
	public Game updateGame(Integer id, Game game) {
		Game newGame = this.getGameById(id);
		
		if(newGame != null) {
			newGame.setName(game.getName());
			newGame.setType(game.getType());
			newGame.setGenre(game.getGenre());
			// remember to update picture attribute by the time we have it
		}
		
		// remember to check if newGame is null in dependent functions
		return newGame;
	}
	
	public boolean delete(Integer id) {
		Game newGame = this.getGameById(id);
		
		if(newGame != null) {
			this.gameRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public List<Game> getGameByName(String name) {
		return this.gameRepository.findAllByName(name);
	}
}
