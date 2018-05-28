package com.accenture.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.entities.User;
import com.accenture.repositories.UserRepository;

public class UserService {
	
	@Autowired
	private UserRepository userrepository;
	
	public void createUser(User user) {
		User u=userrepository.save(user);
	}
	
	public void deleteUser(long id) {
		User user=userrepository.findById(id).get();
		userrepository.delete(user);
	}
	
	public void updateUser(long id,String name) {
		User user=userrepository.findById(id).get();
		user.setName(name);
		userrepository.save(user);
		
	}
	public User findUser(long id) {
		User user=userrepository.findById(id).get();
		return user;
	}

}
