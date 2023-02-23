package com.learnsecurity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnsecurity.model.User;
import com.learnsecurity.services.UserService;

@RestController // will return result in json format
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// all users
	@GetMapping("/")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}
	
	// return single user
//	@PreAuthorize("hasRole('ADMIN')")  // by this ony admin can access the api 
	// and for this u need to add @EnableGlobalMethodSecurity in class level in configuration 
	@GetMapping("/{username}")
	public User getUser(@PathVariable  String username) {
		return this.userService.getUser(username);
	}
	
	// add user
	@PostMapping("/")
	public User add(@RequestBody  User user) {
		return this.userService.addUser(user);
	}
	

}
