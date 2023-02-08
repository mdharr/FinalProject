package com.skilldistillery.skillswap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	
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

//	@Override
//	public Project findByUsername(String username) {
//		return ;
//	}
}
