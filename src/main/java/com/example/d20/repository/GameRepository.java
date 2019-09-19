package com.example.d20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.d20.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
	List<Game> findAllByName(String name);
}
