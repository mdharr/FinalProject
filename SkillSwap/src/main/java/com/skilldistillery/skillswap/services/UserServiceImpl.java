package com.skilldistillery.skillswap.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.skillswap.entities.Address;
import com.skilldistillery.skillswap.entities.User;
import com.skilldistillery.skillswap.repositories.AddressRepository;
import com.skilldistillery.skillswap.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User show(int id) {
		User user = null;
		Optional<User> userOpt = userRepo.findById(id);
		if (userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public User register(User user) {
		user.setAvailability(true);
		return userRepo.saveAndFlush(user);
	}

	@Override
	public User showByUsername(String username) {
		User user = null;
		//Optional<User> userOpt = userRepo.findById(id);
		
		User userOpt = userRepo.findByUsername(username);
		if (userOpt != null) {
			user = userOpt;
		}
		System.out.println(user.getFirstName());
		return user;
	}
	
	@Override
	public User updateAdmin(int id, User user) {
		User userUpdate = show(id);
		Optional<User> addressCheck = userRepo.findById(id);
		User origUser = addressCheck.get();
		if (origUser != null && user.getAddress() != null) {
			userUpdate.setAddress(user.getAddress());
		}

		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		userUpdate.setEnabled(user.getEnabled());
		userUpdate.setAvailability(user.getAvailability());
		userUpdate.setEmail(user.getEmail());
		userUpdate.setBio(user.getBio());
		userUpdate.setProfileImage(user.getProfileImage());
	
		return userRepo.save(userUpdate);
	}

	@Override
	public User updateOwn(String username, User user) {
		
		User userUpdate = userRepo.findByUsername(username);
		System.out.println(username + "username ");
		Address addressCheck = user.getAddress();
		System.out.println(addressCheck);
		if (addressCheck != null) {
			userUpdate.setAddress(addressRepo.saveAndFlush(user.getAddress()));
		}
		userUpdate.setFirstName(user.getFirstName());
		userUpdate.setLastName(user.getLastName());
		//userUpdate.setEnabled(user.getEnabled());
		userUpdate.setAvailability(user.getAvailability());
		userUpdate.setEmail(user.getEmail());
		userUpdate.setBio(user.getBio());
		userUpdate.setProfileImage(user.getProfileImage());
	
		return userRepo.save(userUpdate);
	}

	@Override
	public boolean archiveUser(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			user.setEnabled(false);
			userRepo.saveAndFlush(user);
		}
		return true;
	}

	@Override
	public boolean deleteAdmin(int id) {
		userRepo.deleteById(id);
		return !userRepo.existsById(id);
	}

	@Override
	public void updateLogInTime(User user) {
		Optional<User> userOpt = userRepo.findById(user.getId());
		if (userOpt.isPresent()) {
			User u = userOpt.get();
			u.setLastActive(LocalDate.now());
			userRepo.saveAndFlush(u);
		}
	}
}
