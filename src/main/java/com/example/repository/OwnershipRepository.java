package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Ownership;
import com.example.model.User;

@Repository
public interface OwnershipRepository extends JpaRepository<Ownership, Integer>{
	List<Ownership> findAllByOwner(User user);
}

