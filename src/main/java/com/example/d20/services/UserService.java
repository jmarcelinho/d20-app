package com.example.d20.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.d20.model.User;
import com.example.d20.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	public User getUserById(Integer id) {
		return this.userRepository.findById(id).get();
	}
	
	public User addUser(User user) {
		return this.userRepository.save(user);
	}
	
	//fazer verificacao para quando usuario for nulo
	public User updateUser(Integer id, User user) {
		User newUser = this.getUserById(id);
		
		if(newUser != null) {
			newUser.setId(id);
			newUser.setCpf(user.getCpf());
			newUser.setFname(user.getFname());
			newUser.setLname(user.getLname());
			newUser.setTelephone(user.getTelephone());
		}
		this.userRepository.save(newUser);
		return newUser;
	}
	
	public boolean delete(Integer id) {
		User newUser = this.getUserById(id);
		
		if(newUser != null) {
			this.userRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	
	public List<User> getUserByFname(String name){
		return this.userRepository.findAllByFname(name);
	}
	
	public List<User> getUserByLname(String name){
		return this.userRepository.findAllByLname(name);
	}
	
	public User getUserByEmail(String name){
		return this.userRepository.findByEmail(name);
	}
}