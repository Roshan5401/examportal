package com.exam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception
	{
		Set<UserRole> roles=new HashSet<>();
		return this.userService.createUser(user, roles);
	}
	
	//get the user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) throws Exception {
		return this.userService.getUser(username);
	}
	
	//delete the user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> getUser(@PathVariable("id") Long id) throws Exception {
		this.userService.deleteUser(id);
		return new ResponseEntity<String>("User has been deleted", HttpStatus.OK);
	}
	
	//update User
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") Long id) throws Exception
	{
		User updatedUser=this.userService.updateUser(user,id);
		return new ResponseEntity<>(updatedUser,HttpStatus.OK);
	}
	
	
}
