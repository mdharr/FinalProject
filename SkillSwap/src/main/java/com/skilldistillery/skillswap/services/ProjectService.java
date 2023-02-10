package com.skilldistillery.skillswap.services;

import java.util.List;

import com.skilldistillery.skillswap.entities.Project;

public interface ProjectService {

	List<Project> index();
	
	List<Project> findByUsername(String username);

	List<Project> findByDescription(String name);
	
	Project createProject(String username, Project project);
	
	Project update(int userId, int projectId, Project project);

	boolean archive(int projectId);
	
	Project updateSkills(int skillId, int projectId);
	
}
