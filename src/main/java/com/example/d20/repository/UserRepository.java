package com.example.d20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.d20.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByCpf(String cpf);
	List<User> findAllByName(String name);
}
