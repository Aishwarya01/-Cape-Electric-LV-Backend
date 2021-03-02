package com.capeelectric.service;

import org.springframework.http.ResponseEntity;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;

public interface UserDetailsService {

	public User saveUser(User user) throws UserException;
	
	public ResponseEntity<String> findByUserName(String email);
	
	public User updateUser(String email, String password) throws UserException;
}
