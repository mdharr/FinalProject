package com.skilldistillery.skillswap.entities;

import java.time.LocalDateTime;
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

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "date_posted")
	private LocalDateTime datePosted;
	
	private String description;
	
	@Column(name = "active_status")
	private Boolean activeStatus;
	
	@Column(name = "image_primary")
	private String imagePrimary;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "projected_date")
	private LocalDateTime projectedDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "project")
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@ManyToMany
	@JoinTable(name="project_member", 
	joinColumns = @JoinColumn(name="user_id"), 
	inverseJoinColumns = @JoinColumn(name="project_id"))
	private List<User> users;
	
	@OneToMany(mappedBy = "project")
	private List <ProjectImage> projectImages;

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

	public LocalDateTime getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDateTime datePosted) {
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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getProjectedDate() {
		return projectedDate;
	}

	public void setProjectedDate(LocalDateTime projectedDate) {
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

	public Project(int id, String name, LocalDateTime datePosted, String description, Boolean activeStatus,
			String imagePrimary, LocalDateTime startDate, LocalDateTime projectedDate, User user) {
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
}
