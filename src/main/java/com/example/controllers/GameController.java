package com.example.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Game;
import com.example.services.GameService;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public List<Game> getAll() {
		return this.gameService.getAllGames();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> getGameById(@PathVariable Integer id){
		Game game = this.gameService.getGameById(id);
		
		if(game == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(game);
	}
	
	@PostMapping
	public ResponseEntity<Game> add(@Valid @RequestBody Game gameBody){
		Game game = this.gameService.addGame(gameBody);
		return ResponseEntity.ok(game);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Game> update(@PathVariable Integer id, @Valid @RequestBody Game gameBody){
		Game game = this.gameService.updateGame(id, gameBody);
		if(game == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(game);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		boolean t = this.gameService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
