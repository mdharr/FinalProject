package com.skilldistillery.skillswap.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_skill")
public class UserSkill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "skill_id")
	private int skillId;
	@Column(name = "experience_level_id")
	private Integer experienceLevelId;
	private String description;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public Integer getExperienceLevelId() {
		return experienceLevelId;
	}

	public void setExperienceLevelId(Integer experienceLevelId) {
		this.experienceLevelId = experienceLevelId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserSkill(int userId, int skillId, Integer experienceLevelId, String description) {
		super();
		this.userId = userId;
		this.skillId = skillId;
		this.experienceLevelId = experienceLevelId;
		this.description = description;
	}

	public UserSkill() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSkill other = (UserSkill) obj;
		return userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserSkill [userId=" + userId + ", skillId=" + skillId + ", experienceLevelId=" + experienceLevelId
				+ ", description=" + description + "]";
	}

}
