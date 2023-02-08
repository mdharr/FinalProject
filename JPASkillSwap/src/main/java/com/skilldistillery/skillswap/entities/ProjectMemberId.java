package com.skilldistillery.skillswap.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name="project_member")
public class ProjectMemberId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="project_id")
	private int projectId;

	public int getUserId() {
		return userId;
	}

	public ProjectMemberId() {
		super();
	}

	public ProjectMemberId(int userId, int projectId) {
		super();
		this.userId = userId;
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "ProjectMemberId [userId=" + userId + ", projectId=" + projectId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectMemberId other = (ProjectMemberId) obj;
		return projectId == other.projectId && userId == other.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
