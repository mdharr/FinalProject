package com.skilldistillery.skillswap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillswap.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findByUser_UsernameLike(String username);
}