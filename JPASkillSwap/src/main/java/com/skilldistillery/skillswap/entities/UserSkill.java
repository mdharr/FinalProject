package com.skilldistillery.skillswap.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_skill")
public class UserSkill {
	
	@EmbeddedId
	private UserSkillId id;
	
	@Column(name="experience_level_id")
	private Integer experienceId;
	
	private String description;

	public UserSkillId getId() {
		return id;
	}

	public void setId(UserSkillId id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserSkill [id=" + id + ", experienceId=" + experienceId + ", description=" + description + "]";
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
		UserSkill other = (UserSkill) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getExperienceId() {
		return experienceId;
	}

	public void setExperienceId(Integer experienceId) {
		this.experienceId = experienceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
