package com.skilldistillery.skillswap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.skillswap.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	
	Skill findByNameLike(String name);

}
