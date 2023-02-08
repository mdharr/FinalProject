package com.skilldistillery.skillswap.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.services.UserService;

@CrossOrigin({ "*", "http://localhost/" })
@RestController
@RequestMapping("api")
public class AdminController {

	@Autowired
	private UserService userService;

	@PutMapping("users/admin/{id}")
	public User adminUpdate(@PathVariable Integer id, @RequestBody User user, HttpServletResponse res) {

		try {
			user = userService.updateAdmin(id, user);

			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			user = null;
		}

		return user;
	}

	@DeleteMapping("users/admin/{id}")
	public void deleteAdmin(@PathVariable int id, HttpServletRequest req, HttpServletResponse res) {
		try {
			if (userService.deleteAdmin(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);

		}

	}
}
