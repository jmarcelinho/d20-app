package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

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
			newUser.setCpf(user.getCpf());
			newUser.setBankInfo(user.getBankInfo());
			newUser.setCredCard(user.getCredCard());
			newUser.setName(user.getName());
			newUser.setTelephone(user.getTelephone());
		}
		
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
	
	public List<User> getUserByNome(String name){
		return this.userRepository.findAllByName(name);
	}
}
