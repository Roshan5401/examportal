package com.exam.service;
import java.util.*;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {
	
	//creating user
	public User createUser(User user,Set<UserRole> userRoles) throws Exception;
	
}
