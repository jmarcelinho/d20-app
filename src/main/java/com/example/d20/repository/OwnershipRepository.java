package com.example.d20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.d20.model.Ownership;
import com.example.d20.model.User;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Integer>{
	List<Ownership> findAllByOwner(User user);
}

