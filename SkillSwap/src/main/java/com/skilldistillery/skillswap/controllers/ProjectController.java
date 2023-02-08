package com.skilldistillery.skillswap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("projects/{username}")
	public List<Project> showByUsername(@PathVariable String username, HttpServletRequest req,
			HttpServletResponse res){
		List<Project> proj = projectService.findByUsername(username);
		if(proj.isEmpty()) {
			res.setStatus(404);
		}
		return proj;
	}
}
