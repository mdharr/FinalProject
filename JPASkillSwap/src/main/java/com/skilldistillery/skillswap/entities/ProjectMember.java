package com.skilldistillery.skillswap.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProjectMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int projectId;
	private String comments;
	private Integer rating;
	private String rating_comments;

//	@ManyToOne
//	@JoinColumn(name="project_id")
//	private Project project;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	public ProjectMember() {}

	public int getId() {
		return id;
	}

	public ProjectMember(int id, int projectId, String comments, Integer rating, String rating_comments,
			User user) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.comments = comments;
		this.rating = rating;
		this.rating_comments = rating_comments;
//		this.project = project;
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getRating_comments() {
		return rating_comments;
	}

	public void setRating_comments(String rating_comments) {
		this.rating_comments = rating_comments;
	}
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

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
		ProjectMember other = (ProjectMember) obj;
		return id == other.id;
	}
	
	
	
}
