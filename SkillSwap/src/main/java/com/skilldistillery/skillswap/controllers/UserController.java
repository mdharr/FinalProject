package com.skilldistillery.skillswap.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.services.UserService;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public List<User> index() {
		return userService.index();
	}
	
	

}
