package com.skilldistillery.skillswap.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name = "user_skill")
public class UserSkillId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "skill_id")
	private int skillId;

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

	public UserSkillId(int userId, int skillId) {
		super();
		this.userId = userId;
		this.skillId = skillId;

	}

	public UserSkillId() {
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
		UserSkillId other = (UserSkillId) obj;
		return userId == other.userId;
	}

	@Override
	public String toString() {
		return "UserSkillId [userId=" + userId + ", skillId=" + skillId + "]";
	}

}
