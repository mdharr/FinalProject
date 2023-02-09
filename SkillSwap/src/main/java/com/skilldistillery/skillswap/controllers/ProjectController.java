package com.skilldistillery.skillswap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("projects/description/{name}")
	public List<Project> showByComment(@PathVariable String name, HttpServletRequest req,
			HttpServletResponse res){
		List<Project> proj = projectService.findByDescription(name);
		if(proj.isEmpty()) {
			res.setStatus(404);
		}
		return proj;
	}
	
	@PostMapping("users/{id}/projects")
	public Project createProject(@PathVariable int id, @RequestBody Project project, HttpServletResponse res, HttpServletRequest req) {
		Project newProject = null;
		try {
			newProject = projectService.createProject(id, project);
			res.setStatus(201);
		//	StringBuffer url = req.getRequestURL();
		//	url.append("/").append(user.getId());
		//	res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		return newProject;
	}
	
	
}
