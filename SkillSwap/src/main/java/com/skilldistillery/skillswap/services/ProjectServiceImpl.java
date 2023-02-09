package com.skilldistillery.skillswap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.repositories.ProjectRepository;
import com.skilldistillery.skillswap.repositories.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Project> index(){
		return projectRepo.findAll();
	}

	@Override
	public List<Project> findByUsername(String username) {
		List<Project> project = new ArrayList<>();
		List<Project> projectOpt = projectRepo.findByUser_UsernameLike(username);
		if(!projectOpt.isEmpty()) {
			project.addAll(projectOpt);
		}
		return project;
	}

	@Override
	public List<Project> findByDescription(String name) {
		List<Project> project = new ArrayList<>();
		List<Project> projectOpt = projectRepo.findByDescriptionContaining(name);
		if(!projectOpt.isEmpty()) {
			project.addAll(projectOpt);
		}
		return project;
	}
	
	@Override
	public Project createProject(int userId, Project project) {
		Project projectNew = null;
		User user = null;
		Optional<User> userOpt = userRepo.findById(userId);
		if (userOpt.isPresent()) {
			user = userOpt.get();
			project.setUser(user);
			projectNew = projectRepo.saveAndFlush(project);
		}
		return projectNew;
	}
}
