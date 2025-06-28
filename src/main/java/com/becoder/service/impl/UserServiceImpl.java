package com.becoder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.becoder.model.User;
import com.becoder.repository.UserRepository;
import com.becoder.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public User saveUser(User user) {
		User savedUser = userRepository.save(user);
		
		return savedUser;
	}
	@Override
	public List<User> getAllUser() {
		List<User> allUser = userRepository.findAll();
		return allUser;
	}
	@Override
	public User updateUser(User user) {
		//userRepository.findById(id);
		return userRepository.save(user);
	}
	@Override
	public void deleteUser(Integer userId) {
		Optional<User> findByIdUser = userRepository.findById(userId);
		if(findByIdUser.isPresent()) {
			User user = findByIdUser.get();
			userRepository.delete(user);
		}
	}
	@Override
	public User getUserById(Integer Id) {
		Optional<User> findById = userRepository.findById(Id);
		if(findById.isPresent()) {
		  return findById.get();
		}
		return null;
	}
	
	

}
