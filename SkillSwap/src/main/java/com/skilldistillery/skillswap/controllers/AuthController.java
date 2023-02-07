package com.skilldistillery.skillswap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.skilldistillery.skillswap.services.AuthService;

@Controller
@CrossOrigin({"*", "http://localhost"})
public class AuthController {
  @Autowired
  private AuthService authService;
}