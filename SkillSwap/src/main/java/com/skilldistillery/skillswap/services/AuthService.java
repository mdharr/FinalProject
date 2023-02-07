package com.skilldistillery.skillswap.services;

import com.skilldistillery.skillswap.entities.User;

public interface AuthService {
	
	public User register(User user);
	public User getUserByUsername(String username);

}
