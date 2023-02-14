package com.skilldistillery.skillswap.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.skillswap.entities.Skill;
import com.skilldistillery.skillswap.repositories.SkillRepository;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepo;

	@Override
	public List<Skill> index() {
		return skillRepo.findAll();
	}
	
	@Override
	public Skill show(int id) {
		Skill skill = null;
		Optional<Skill> skillOpt = skillRepo.findById(id);
		if (skillOpt.isPresent()) {
			skill = skillOpt.get();
		}
		return skill;
	}
	
	@Override
	public List <Skill> findSkills(String name) {
		List <Skill> allSkills = skillRepo.findAll();
		for (Skill skill : allSkills) {
			if(skill.getName() == name) {
				allSkills.add(skill);
			} else {
				return null;
			}
		}
		return allSkills;
	}

	@Override
	public Skill create(Skill skill) {
		return skillRepo.saveAndFlush(skill);
	}

	@Override
	public Skill update(int id, Skill skill) {
		Skill skillUpdate = show(id);
		skillUpdate.setName(skill.getName());
		skillUpdate.setDescription(skill.getDescription());
		skillUpdate.setImageUrl(skill.getImageUrl());
		return skillRepo.save(skillUpdate);
	}

	@Override
	public boolean destroy(int id) {
		skillRepo.deleteById(id);
		return !skillRepo.existsById(id);
	}

}
