package com.exam.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
