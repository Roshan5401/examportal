package com.exam.controller;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
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
}
