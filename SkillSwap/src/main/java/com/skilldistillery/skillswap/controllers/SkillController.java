package com.skilldistillery.skillswap.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.skillswap.entities.Skill;
import com.skilldistillery.skillswap.services.SkillService;

@CrossOrigin({"*", "http://localhost/"})
@RestController
@RequestMapping("api")
public class SkillController {


	@Autowired
	private SkillService skillService;
	
	@GetMapping("skills")
	public List<Skill> index() {
		return skillService.index();
	}
	
	@GetMapping("skills/{id}")
	public Skill show(@PathVariable Integer id, HttpServletResponse res) {
		Skill skill = skillService.show(id);
		if (skill == null) {
			res.setStatus(404);
		}
		return skill;
	}
	
	@GetMapping("skills/filter/{name}")
	public List <Skill> search(@PathVariable String name) {
		return skillService.findSkills(name);
	}
	@PostMapping("skills")
	public Skill create(@RequestBody Skill skill, HttpServletResponse res, HttpServletRequest req) {

		try {
			skillService.create(skill);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(skill.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
		}
		return skill;
	}
	
	@PutMapping("skills/{id}")
	public Skill update(@PathVariable Integer id, @RequestBody Skill skill, HttpServletResponse res) {

		try {
			skill = skillService.update(id, skill);

			if (skill == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			skill= null;
		}

		return skill;
	}

	
	@DeleteMapping("skills/{id}")
	public void destroy(@PathVariable Integer id, HttpServletResponse res) {
		try {
			if (skillService.destroy(id)) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
