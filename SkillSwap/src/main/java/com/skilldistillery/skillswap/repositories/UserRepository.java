package com.skilldistillery.skillswap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillswap.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
}
