package com.skilldistillery.skillswap.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Project;
import com.skilldistillery.skillswap.entities.ProjectMember;
import com.skilldistillery.skillswap.entities.ProjectMemberId;
import com.skilldistillery.skillswap.entities.Skill;
import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.repositories.ProjectMemberRepository;
import com.skilldistillery.skillswap.repositories.ProjectRepository;
import com.skilldistillery.skillswap.repositories.SkillRepository;
import com.skilldistillery.skillswap.repositories.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private SkillRepository skillRepo;
	@Autowired
	private ProjectMemberRepository projectMemRepo;

	@Override
	public List<Project> index() {
		return projectRepo.findAll();
	}

	@Override
	public List<Project> findByUsername(String username) {
		List<Project> project = new ArrayList<>();
		List<Project> projectOpt = projectRepo.findByUser_UsernameLike(username);
		if (!projectOpt.isEmpty()) {
			project.addAll(projectOpt);
		}
		return project;
	}

	@Override
	public List<Project> findCompletedProjects(String username) {
		List<Project> completed = new ArrayList<>();
		List<Project> usersProjects = findByUsername(username);
		for (Project project : usersProjects) {
			if (project.getEnabled() == false) {
				completed.add(project);
			}
		}
		return completed;

	}

	@Override 
	public List<Project> findBySkills(String name){
		List<Project> proj = new ArrayList<>();
		List<Project> present = projectRepo.findBySkills_NameLike(name);
		if(!present.isEmpty()) {
			proj.addAll(present);
		}
		return proj;
	}
	
	@Override
	public List<Project> findByDescription(String name) {
		List<Project> project = new ArrayList<>();
		List<Project> projectOpt = projectRepo.findByDescriptionContaining(name);
		if (!projectOpt.isEmpty()) {
			project.addAll(projectOpt);
		}
		return project;
	}

	@Override
	public Project createProject(String username, Project project) {
		Project projectNew = null;
		User user = null;
		User userOpt = userRepo.findByUsername(username);
		if (userOpt != null) {
			user = userOpt;
			project.setUser(user);
			project.setEnabled(true);
			projectNew = projectRepo.saveAndFlush(project);
		}
		return projectNew;
	}

	@Override
	public Project update(String username, Project project) {
		Optional<Project> proj = projectRepo.findById(project.getId());
		User user = null;
		Project update = null;
		User userOpt = userRepo.findByUsername(username);
		if (userOpt != null && proj.isPresent()) {
			user = userOpt;
			update = proj.get();

			update.setId(project.getId());
			update.setUser(user);
			update.setName(project.getName());
			update.setDatePosted(project.getDatePosted());
			update.setDescription(project.getDescription());
			update.setActiveStatus(project.getActiveStatus());
			update.setImagePrimary(project.getImagePrimary());
			update.setStartDate(project.getStartDate());
			update.setProjectedDate(project.getProjectedDate());
			update.setEnabled(project.getEnabled());
		}
		return projectRepo.save(update);
	}

	@Override
	public boolean archive(int projectId) {
		Optional<Project> projOpt = projectRepo.findById(projectId);
		if (projOpt.isPresent()) {
			Project project = projOpt.get();
			project.setEnabled(false);
			projectRepo.saveAndFlush(project);
		}
		return true;
	}

	@Override
	public Project updateSkills(int skillId, int projectId) {
		Optional<Skill> skillOpt = skillRepo.findById(skillId);
		Optional<Project> projectOpt = projectRepo.findById(projectId);
		Skill skill = null;
		Project project = null;

		if (skillOpt.isPresent() && projectOpt.isPresent()) {
			skill = skillOpt.get();
			project = projectOpt.get();

			if (project.getSkills().contains(skill)) {
				project.removeSkill(skill);
				skill.removeProject(project);

			} else {
				project.addSkill(skill);
				skill.addProject(project);
			}
			skillRepo.saveAndFlush(skill);

			projectOpt = projectRepo.findById(projectId);
			project = projectOpt.get();
		}

		return project;
	}

	@Override
	public Project addUser(String username, int projectId) {
		Optional<Project> projectOpt = projectRepo.findById(projectId);
		User userOpt = userRepo.findByUsername(username);
		User user = null;
		ProjectMember member = new ProjectMember();
		Project project = null;
		if (userOpt != null && projectOpt.isPresent()) {
			user = userOpt;
			project = projectOpt.get();
			// project.addUser(user);
			ProjectMemberId id = new ProjectMemberId();
			id.setProjectId(projectId);
			id.setUserId(user.getId());
			member.setProject(project);
			member.setUser(user);
			member.setId(id);

		}
		projectMemRepo.saveAndFlush(member);

		return project;
	}
}
