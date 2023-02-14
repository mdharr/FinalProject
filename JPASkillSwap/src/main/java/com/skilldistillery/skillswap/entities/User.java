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
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private Boolean enabled;

	private Boolean availability;

	private String email;

	private String bio;

	@Column(name = "profile_image")
	private String profileImage;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;


	@Column(name = "last_active")
	private LocalDate lastActive;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

//	@ManyToMany
//	@JoinTable(name = "user_skill", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
//	private List<Skill> skills;

	
	@OneToMany(mappedBy = "user")
	private List<UserSkill> userSkills;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Project> projectOwner;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "project_member", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projectsHelper;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "followed_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "followed_user_id"))
	private List<User> following;

	@JsonIgnore
	@ManyToMany(mappedBy = "following")
	private List<User> followedBy;

	private String role;

	public User() {
	}

	public User(int id, String username, String password, String firstName, String lastName, Boolean enabled,
			Boolean availability, String email, String bio, String profileImage, LocalDateTime createdDate,
			LocalDate lastActive, List<Comment> comments, Address address, List<UserSkill> userSkills,
			List<Project> projectOwner, List<Project> projectsHelper, List<User> following, List<User> followedBy,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.availability = availability;
		this.email = email;
		this.bio = bio;
		this.profileImage = profileImage;
		this.createdDate = createdDate;
		this.lastActive = lastActive;
		this.comments = comments;
		this.address = address;
		this.userSkills = userSkills;
		this.projectOwner = projectOwner;
		this.projectsHelper = projectsHelper;
		this.following = following;
		this.followedBy = followedBy;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getLastActive() {
		return lastActive;
	}

	public void setLastActive(LocalDate lastActive) {
		this.lastActive = lastActive;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<User> getFollowedBy() {
		return followedBy;
	}

	public void setFollowedBy(List<User> followedBy) {
		this.followedBy = followedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public List<Skill> getSkills() {
//		return skills;
//	}
//
//	public void setSkills(List<Skill> skills) {
//		this.skills = skills;
//	}

	public List<Project> getProjectsHelper() {
		return projectsHelper;
	}

	public void setProjectsHelper(List<Project> projectsHelper) {
		this.projectsHelper = projectsHelper;
	}

	public List<Project> getProjectOwner() {
		return projectOwner;
	}

	public void setProjectOwner(List<Project> projectOwner) {
		this.projectOwner = projectOwner;
	}

	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
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
		User other = (User) obj;
		return id == other.id;
	}

	public void addProjectHelper(Project project) {
		if (projectsHelper == null) {
			projectsHelper = new ArrayList<>();
		}
		if (!projectsHelper.contains(project)) {
			projectsHelper.add(project);
			project.addUser(this);
		}
	}

	public void removeProjectHelper(Project project) {
		if (projectsHelper != null && projectsHelper.contains(project)) {
			projectsHelper.remove(project);
			project.removeUser(this);
		}
	}

	public void addUserSkill(UserSkill userSkill) {
		if (userSkills == null) {
			userSkills = new ArrayList<>();
		}
		if (!userSkills.contains(userSkill)) {
			userSkills.add(userSkill);
			userSkill.setUser(this);
		}
	}

	public void removeUserSkill(UserSkill userSkill) {
		if (userSkills != null && userSkills.contains(userSkill)) {
			userSkills.remove(userSkill);
			userSkill.setUser(null);
		}
	}

	public User addFollower(User friend) {
		following.add(friend);
		return friend;
	}

	public User removeFollower(User friend) {
		following.remove(friend);
		return friend;
	}

	public User addFollowedBy(User friend) {
		followedBy.add(friend);
		return friend;
	}
	public User removeFollowedBy(User friend) {
		followedBy.add(friend);
		return friend;
	}
	
	public void addProjectOwner(Project project) {
		if (projectOwner == null) {
			projectOwner = new ArrayList<>();
		}
		if (!projectOwner.contains(project)) {
			projectOwner.add(project);
			project.addUser(this);
		}
	}

	public void removeProjectOwner(Project project) {
		if (projectOwner != null && projectOwner.contains(project)) {
			projectOwner.remove(project);
			project.removeUser(this);
		}
	}

	public void addComment(Comment comment) {
		if (comment == null) {
			comments = new ArrayList<>();
		}
		if (!comments.contains(comment)) {
			comments.add(comment);
			comment.setUser(this);
		}
	}

	public void removeComment(Comment comment) {
		if (comments != null && comments.contains(comment)) {
			comments.remove(comment);
			comment.setUser(null);
		}
	}

	@Override
	public String toString() {
		return "User id=" + id;
	}

}
