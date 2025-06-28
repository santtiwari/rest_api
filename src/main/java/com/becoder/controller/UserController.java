package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.model.User;
import com.becoder.service.impl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@ RequestBody User user){
		User saveUser = userServiceImpl.saveUser(user);
		if(ObjectUtils.isEmpty(saveUser)) {
			return new ResponseEntity<>("user not saved or null", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}
	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllUser(){
		List<User> allUser = userServiceImpl.getAllUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@ RequestBody User user){
		
		User updateUser = userServiceImpl.updateUser(user);
		if(ObjectUtils.isEmpty(updateUser)) {
			return new ResponseEntity<>("user is not updated null", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id ){
		userServiceImpl.deleteUser(id);
		return new ResponseEntity<>("user is deleted successfully",HttpStatus.OK);
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserByid(@PathVariable Integer id){
		User userById = userServiceImpl.getUserById(id);
		if(ObjectUtils.isEmpty(userById)) {
			return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}

}
