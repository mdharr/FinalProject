package com.skilldistillery.skillswap.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "project_member")
public class ProjectMember {
	@EmbeddedId
	private ProjectMemberId id;

	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId(value="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	@MapsId(value = "projectId")
	private Project project;
	private String comments;
	private Integer rating;

	@Column(name = "rating_comments")
	private String ratingComments;

	public ProjectMember() {
	}

	public ProjectMember(ProjectMemberId id, User user, Project project, String comments, Integer rating,
			String ratingComments) {
		super();
		this.id = id;
		this.user = user;
		this.project = project;
		this.comments = comments;
		this.rating = rating;
		this.ratingComments = ratingComments;
	}

	public ProjectMemberId getId() {
		return id;
	}

	public void setId(ProjectMemberId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public String getRatingComments() {
		return ratingComments;
	}

	public void setRatingComments(String ratingComments) {
		this.ratingComments = ratingComments;
	}

	@Override
	public String toString() {
		return "ProjectMember [id=" + id + ", user=" + user + ", project=" + project + ", comments=" + comments
				+ ", rating=" + rating + ", ratingComments=" + ratingComments + "]";
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
		return Objects.equals(id, other.id);
	}

}