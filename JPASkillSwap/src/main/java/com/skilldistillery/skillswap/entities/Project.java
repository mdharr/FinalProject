package com.skilldistillery.skillswap.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@CreationTimestamp
	@Column(name = "date_posted")
	private LocalDate datePosted;

	private String description;

	@Column(name = "active_status")
	private Boolean activeStatus;

	@Column(name = "image_primary")
	private String imagePrimary;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "projected_date")
	private LocalDate projectedDate;

	@ManyToMany
	@JoinTable(name="project_has_skill", 
	joinColumns=@JoinColumn(name="project_id"),
	inverseJoinColumns= @JoinColumn(name="skill_id"))
	private List<Skill> skills;
	

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "project")
	private List<Comment> comments;
	
	private Boolean enabled;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@ManyToMany
	@JoinTable(name = "project_member", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<User> users;

	@OneToMany(mappedBy = "project")
	private List<ProjectImage> projectImages;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", datePosted=" + datePosted + ", description=" + description
				+ ", activeStatus=" + activeStatus + ", imagePrimary=" + imagePrimary + ", startDate=" + startDate
				+ ", projectedDate=" + projectedDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDate datePosted) {
		this.datePosted = datePosted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getImagePrimary() {
		return imagePrimary;
	}

	public void setImagePrimary(String imagePrimary) {
		this.imagePrimary = imagePrimary;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getProjectedDate() {
		return projectedDate;
	}

	public void setProjectedDate(LocalDate projectedDate) {
		this.projectedDate = projectedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ProjectImage> getProjectImages() {
		return projectImages;
	}

	public void setProjectImages(List<ProjectImage> projectImages) {
		this.projectImages = projectImages;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Project(int id, String name, LocalDate datePosted, String description, Boolean activeStatus,
			String imagePrimary, LocalDate startDate, LocalDate projectedDate, User user) {
		super();
		this.id = id;
		this.name = name;
		this.datePosted = datePosted;
		this.description = description;
		this.activeStatus = activeStatus;
		this.imagePrimary = imagePrimary;
		this.startDate = startDate;
		this.projectedDate = projectedDate;
	}

	public Project() {
		super();
	}

	public void addUser(User user) {
		if (user == null) {
			users = new ArrayList<>();
		}
		if (!users.contains(user)) {
			users.add(user);
			user.addProjectHelper(this);
		}
	}

	public void removeUser(User user) {
		if (users != null && users.contains(user)) {
			users.remove(user);
			user.removeProjectHelper(this);
		}
	}

	public void addComment(Comment comment) {
		if (comment == null) {
			comments = new ArrayList<>();
		}
		if (!comments.contains(comment)) {
			comments.add(comment);
			comment.setProject(this);
		}
	}

	public void removeComment(Comment comment) {
		if (comments != null && comments.contains(comment)) {
			comments.remove(comment);
			comment.setProject(null);
		}
	}
	
	public void addSkill(Skill skill) {
		if (skills == null) {
			skills = new ArrayList<>();
		}
		if (!skills.contains(skill)) {
			skills.add(skill);
			skill.addProject(this);
		}
	}

	public void removeSkill(Skill skill) {
		if (skills != null && skills.contains(skill)) {
			skills.remove(skill);
			skill.removeProject(this);
		}
	}
	
	
	
}
