package com.skilldistillery.skillswap.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project_member")
public class ProjectMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	@Column(name="project_id")
	private int projectId;
	private String comments;
	private Integer rating;
	@Column(name="rating_comments")
	private String ratingComments;
	
	public ProjectMember() {}

	public int getId() {
		return id;
	}

	public ProjectMember(int id, int projectId, String comments, Integer rating, String ratingComments,
			User user) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.comments = comments;
		this.rating = rating;
		this.ratingComments = ratingComments;

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
		return ratingComments;
	}

	public void setRating_comments(String ratingComments) {
		this.ratingComments = ratingComments;
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
