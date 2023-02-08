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
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private Boolean enabled;
	
	private Boolean availability;
	
	private String email;
	
	private String bio;
	
	@Column(name="profile_image")
	private String profileImage;
	
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@Column(name="last_active")
	private LocalDateTime lastActive;
	
	@OneToMany(mappedBy = "user")
	private List<Project> projects;
	
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;
	
//	@OneToMany(mappedBy = "user")
//	private List<Skill> skills;


	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
	
	@ManyToMany
	@JoinTable(name="user_skill", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="skill_id"))
	private List<Skill> skills;
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@ManyToMany
	@JoinTable(name="followed_user",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="followed_user_id"))
	private List<User> following;
	
	@ManyToMany(mappedBy="following")
	private List<User> followedBy;
	
	private String role;

	public User() {}

	public User(int id, String username, String password, Boolean enabled, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
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
	
	public LocalDateTime getLastActive() {
		return lastActive;
	}
	
	public void setLastActive(LocalDateTime lastActive) {
		this.lastActive = lastActive;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", enabled=" + enabled + ", availability=" + availability + ", email="
				+ email + ", bio=" + bio + ", profileImage=" + profileImage + ", createdDate=" + createdDate
				+ ", lastActive=" + lastActive + ", projects=" + projects + ", comments=" + comments + ", address="
				+ address + ", user=" + ", following=" + following + ", followedBy=" + followedBy + ", role="
				+ role + "]";
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
	
	
}
