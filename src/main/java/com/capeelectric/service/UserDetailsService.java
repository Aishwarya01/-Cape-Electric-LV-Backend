package com.capeelectric.service;

import org.springframework.http.ResponseEntity;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;

public interface UserDetailsService {

	public User saveUser(User user) throws UserException;
	
	public ResponseEntity<String> findByUserName(String email) throws ForgotPasswordException;
	
	public User updatePassword(String email, String password) throws UpdatePasswordException;
	
	public User changePassword(String email, String oldPassword, String password) throws ChangePasswordException;
	
	public User retrieveUserInformation(String email) throws UserException;
	
	public User updateUserProfile(User user);
	
}
