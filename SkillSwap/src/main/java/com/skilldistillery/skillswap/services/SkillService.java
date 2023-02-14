
package com.skilldistillery.skillswap.services;

import java.util.List;

import com.skilldistillery.skillswap.entities.Skill;

public interface SkillService {

	public List<Skill> index();

	public Skill show(int id);
	
	public Skill create(Skill skill);
	
	public Skill update(int id, Skill skill);
	
	public boolean destroy(int id);

	public List <Skill> findSkills(String name);

}
