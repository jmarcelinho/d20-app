package com.example.d20.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d20.model.Ownership;
import com.example.d20.model.User;
import com.example.d20.repository.OwnershipRepository;

@Service
public class OwnershipService {
	@Autowired
	private OwnershipRepository ownershipRepository;
	
	public List<Ownership> getAllOwnerships() {
		return this.ownershipRepository.findAll();
	}
	
	public Ownership getOwnershipById(Integer id) {
		return this.ownershipRepository.findById(id).get();
	}
	
	public Ownership addOwnership(Ownership ownership) {
		return this.ownershipRepository.save(ownership);
	}
	
	public Ownership updateOwnership(Integer id, Ownership ownership) {
		Ownership newOwnership = this.getOwnershipById(id);
		
		if(newOwnership!= null) {
			newOwnership.setPrice(ownership.getPrice());
			newOwnership.setInfo(ownership.getInfo());
		}
		
		// remember to check if newOwnership is null in dependent functions
		return newOwnership;
	}
	
	public boolean delete(Integer id) {
		Ownership newOwnership = this.getOwnershipById(id);
		
		if(newOwnership != null) {
			this.ownershipRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public List<Ownership> getOwnershipByOwner(User owner) {
		return this.ownershipRepository.findAllByOwner(owner);
	}
}
