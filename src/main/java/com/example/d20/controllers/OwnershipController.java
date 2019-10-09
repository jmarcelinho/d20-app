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

import com.example.d20.model.Ownership;
import com.example.d20.services.OwnershipService;

@RestController
@CrossOrigin
@RequestMapping("/ownership")
public class OwnershipController {
	@Autowired
	private OwnershipService ownershipService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<Ownership> getAll() {
		return this.ownershipService.getAllOwnerships();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Ownership> getOwnershipById(@PathVariable Integer id){
		Ownership ownership = this.ownershipService.getOwnershipById(id);
		
		if(ownership == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(ownership);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Ownership> add(@Valid @RequestBody Ownership ownershipBody){
		Ownership ownership = this.ownershipService.addOwnership(ownershipBody);
		return ResponseEntity.ok(ownership);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Ownership> update(@PathVariable Integer id, @Valid @RequestBody Ownership ownershipBody){
		Ownership ownership = this.ownershipService.updateOwnership(id, ownershipBody);
		if(ownership == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ownership);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Void> erase(@PathVariable Integer id) {
		boolean t = this.ownershipService.delete(id);
		
		if (!t) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
