package com.exam.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.exception.ResourceNotFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.UserRepository;
import com.exam.repo.RoleRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception
	{
		User local=this.userRepository.findByUsername(user.getUsername());
		Role role=new Role();
		role.setRoleName("NORMAL");
		
		UserRole userrole=new UserRole();
		userrole.setRole(role);
		userrole.setUser(user);
		userRoles.add(userrole);
		
		if(local!=null)
		{
			System.out.println("User is already there!!");
			throw new Exception("User already present !!");
		}
		else {
			//user create
			//adding the roles to the role table
			for(UserRole u:userRoles)
				roleRepository.save(u.getRole());
			//adding the role to user table
			user.getUserRoles().addAll(userRoles);
			//saving the user
			local=this.userRepository.save(user);
		}
		return local;
	}
	@Override
	public User getUser(String user) throws Exception {
		return this.userRepository.findByUsername(user);
	}
	@Override
	public void deleteUser(Long id) throws Exception {
		this.userRepository.deleteById(id);
		
	}
	@Override
	public User updateUser(User user, Long id) throws Exception {
		User user1=this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","id",id));
		user1.setId(id);
		user1.setFirstname(user.getFirstname());
		user1.setMiddlename(user.getMiddlename());
		user1.setLastname(user.getLastname());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		user1.setUsername(user.getUsername());
		user1.setPhone(user.getPhone());
		User updatedUser=this.userRepository.save(user1);
		return updatedUser;
	}

}
