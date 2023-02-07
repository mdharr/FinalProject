package com.skilldistillery.skillswap.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "project_image")
public class ProjectImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	private String caption;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	public ProjectImage() {
		super();
	}

	public ProjectImage(int id, String imageUrl, String caption, LocalDateTime createdDate, Project project) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.caption = caption;
		this.createdDate = createdDate;
		this.project = project;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "ProjectImage [id=" + id + ", imageUrl=" + imageUrl + ", caption=" + caption + ", createdDate="
				+ createdDate + ", project=" + project + "]";
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
		ProjectImage other = (ProjectImage) obj;
		return id == other.id;
	}
	
	
}
