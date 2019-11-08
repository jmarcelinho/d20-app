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
	public List<Game> getAll() {
		return this.gameService.getAllGames();
	}
	
	@GetMapping("/name/{name}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Game> getGameByName(@PathVariable String name){
		return this.gameService.getGameByName(name);
	}
	
	@GetMapping("/type/{type}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Game> getGameByType(@PathVariable String type){
		return this.gameService.getGameByName(type);
	}
	
	@GetMapping("/genre/{genre}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Game> getGameByGenre(@PathVariable String genre){
		return this.gameService.getGameByName(genre);
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
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Game> add(@Valid @RequestBody Game gameBody){
		Game game = this.gameService.addGame(gameBody);
		return ResponseEntity.ok(game);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Game> update(@PathVariable Integer id, @Valid @RequestBody Game gameBody){
		Game game = this.gameService.updateGame(id, gameBody);
		if(game == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(game);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.gameService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
