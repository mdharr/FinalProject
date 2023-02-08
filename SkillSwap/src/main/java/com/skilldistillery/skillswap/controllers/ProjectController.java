package com.skilldistillery.skillswap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.services.ProjectService;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@GetMapping("projects")
	public List<Project> index(){
		return projectService.index();
	}
}
