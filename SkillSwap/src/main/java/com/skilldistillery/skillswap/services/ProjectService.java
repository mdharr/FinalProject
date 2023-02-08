package com.skilldistillery.skillswap.services;

import java.util.List;

import com.skilldistillery.skillswap.entities.Project;

public interface ProjectService {

	List<Project> index();
	
	Project findByUsername(String username);
}
