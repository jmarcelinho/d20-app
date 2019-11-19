package com.example.d20.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.d20.model.Game;
import com.example.d20.services.GameService;

@RestController
@CrossOrigin
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gameService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> getAll() {
		List<Game> games = this.gameService.getAllGames();
		return ResponseEntity.ok(games);
	}
	
	@GetMapping("/name/{name}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> getGameByName(@PathVariable String name){
		List<Game> games = this.gameService.getGameByName(name);
		return ResponseEntity.ok(games);
	}
	
	@GetMapping("/type/{type}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> getGameByType(@PathVariable String type){
		List<Game> games = this.gameService.getGameByType(type);
		return ResponseEntity.ok(games);
	}
	
	@GetMapping("/genre/{genre}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> getGameByGenre(@PathVariable String genre){
		List<Game> games = this.gameService.getGameByGenre(genre);
		return ResponseEntity.ok(games);
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Game> getGameById(@PathVariable Integer id){
		Game game = this.gameService.getGameById(id);
		
		if(game == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(game);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Game> add(@Valid @RequestBody Game gameBody){
		Game game = this.gameService.addGame(gameBody);
		return ResponseEntity.ok(game);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Game> update(@PathVariable Integer id, @Valid @RequestBody Game gameBody){
		Game game = this.gameService.updateGame(id, gameBody);
		if(game == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(game);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.gameService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
