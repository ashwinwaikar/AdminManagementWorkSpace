package com.smartstream.admin.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartstream.admin.management.model.AppUser;
import com.smartstream.admin.management.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	public AppUser getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public AppUser createUser(AppUser user) {
		return userRepository.save(user);
	}

	public AppUser updateUser(Long id, AppUser userDetails) {
		AppUser user = userRepository.findById(id).orElse(null);
		if (user != null) {
			user.setPassword(userDetails.getPassword());
			user.setEmail(userDetails.getEmail());
			user.setRole(userDetails.getRole());
			return userRepository.save(user);
		}
		return null;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
