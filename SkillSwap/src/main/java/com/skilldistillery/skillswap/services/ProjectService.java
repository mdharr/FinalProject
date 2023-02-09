package com.skilldistillery.skillswap.services;

import java.util.List;

import com.skilldistillery.skillswap.entities.Project;

public interface ProjectService {

	List<Project> index();
	
	List<Project> findByUsername(String username);

	List<Project> findByDescription(String name);

	Project createProject(int userId, Project project);
}
