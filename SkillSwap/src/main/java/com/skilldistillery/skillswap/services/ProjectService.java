package com.skilldistillery.skillswap.services;

import java.util.List;

import com.skilldistillery.skillswap.entities.Project;

public interface ProjectService {

	List<Project> index();
	
	List<Project> findByUsername(String username);

	List<Project> findByDescription(String name);
	
	Project createProject(String username, Project project);
	
	Project update(String username, Project project);

	boolean archive(int projectId);
	
	Project updateSkills(int skillId, int projectId);
	
	Project addUser(String username, int projectId);

	List<Project> findCompletedProjects(String username);

	List<Project> findBySkills(String name);
	
}
