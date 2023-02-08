package com.skilldistillery.skillswap.services;

import java.util.List;

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
	public Project findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Project findByUsername(String username) {
//		return ;
//	}
}
