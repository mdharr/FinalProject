package com.skilldistillery.skillswap.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.repositories.ProjectRepository;
import com.skilldistillery.skillswap.services.ProjectService;

@CrossOrigin({ "*", "http://localhost/" })
@RestController
@RequestMapping("api")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectRepository projectRepo;

	@GetMapping("projects")
	public List<Project> index() {
		return projectService.index();
	}

	@GetMapping("projects/authenticated")
	public List<Project> showByUsername(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		List<Project> proj = projectService.findByUsername(principal.getName());
		if (proj.isEmpty()) {
			res.setStatus(404);
		}
		return proj;
	}

	@GetMapping("projects/description/{name}")
	public List<Project> showByComment(@PathVariable String name, HttpServletRequest req, HttpServletResponse res) {
		List<Project> proj = projectService.findByDescription(name);
		if (proj.isEmpty()) {
			res.setStatus(404);
		}
		return proj;
	}
	
	@GetMapping("projects/completed")
	public List<Project> showByCompleted(HttpServletRequest req, HttpServletResponse res, Principal principal){
		String username = principal.getName();
		List<Project> proj = projectService.findCompletedProjects(username);
		return proj;
	}
	
	@GetMapping("projects/skills/{name}")
	public List<Project> showBySkill(@PathVariable String name, HttpServletRequest req, HttpServletResponse res){
		List<Project> proj = projectService.findBySkills(name);
		if(proj.isEmpty()) {
			res.setStatus(404);
		}
		return proj;
	}
	
	@PostMapping("projects")
	public Project createProject(
			// @PathVariable int id,
			Principal principal, @RequestBody Project project, HttpServletResponse res, HttpServletRequest req) {
		Project newProject = null;
		try {
			newProject = projectService.createProject(principal.getName(), project);
			res.setStatus(201);
			// StringBuffer url = req.getRequestURL();
			// url.append("/").append(user.getId());
			// res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		return newProject;
	}

	@PutMapping("projects/projectId/{pId}/skillId/{sId}")
	public Project updateSkills(Principal principal, @PathVariable("sId") int skillId,
			@PathVariable("pId") int projectId, HttpServletResponse res, HttpServletRequest req) {

		Project proj = projectService.updateSkills(skillId, projectId);
		res.setStatus(200);
		return proj;
	}

	@PutMapping("projects")
	public Project update(Principal principal, @RequestBody Project project, HttpServletRequest req,
			HttpServletResponse res) {
		Project updateProject = null;
		try {
			updateProject = projectService.update(principal.getName(), project);
			res.setStatus(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateProject;
	}

	@PutMapping("projects/{id}")
	public void archiveProject(Principal principal, @PathVariable int id, HttpServletResponse res) {
		try {
			if (projectService.archive(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	@PutMapping("projects/{pid}/users")
	public Project addUser(Principal principal, @PathVariable("pid") int projectId,
			HttpServletResponse res, HttpServletRequest req) {
		Project proj = projectService.addUser(principal.getName(), projectId);
		res.setStatus(200);
		return proj;
	}
}
