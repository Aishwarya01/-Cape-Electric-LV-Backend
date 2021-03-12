package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/**
	 * Method to save the user during the registration
	 */
	public User saveUser(User user) throws UserException {
		logger.debug("Save User Starts");
		Optional<User> createdUser = userRepository.findByUsername(user.getUsername());
		if(createdUser.isPresent() && createdUser.get() != null && createdUser.get().isUserexist()) {
			logger.debug("Save User Ends");
			throw new UserException("User already available");
		} else {
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			user.setUserexist(true);
			user.setCreationdate(LocalDateTime.now ());
			user.setUpdateddate(LocalDateTime.now ());
			logger.debug("Save User Ends");
			return userRepository.save(user);
		}
	}
	
	/**
	 * Method to retrieve the user
	 */
	public ResponseEntity<String> findByUserName(String email) throws UserException{
		// TODO: Email triggering
		logger.debug("Find By User Name Starts");
		if (email != null) {
			Optional<User> optionalUser = userRepository.findByUsername(email);
			if (optionalUser != null && optionalUser.isPresent() && optionalUser.get()!= null) {
				logger.debug("Find By User Name Ends");
				return new ResponseEntity<String>(optionalUser.get().getUsername(), HttpStatus.OK);
			} else {
				logger.debug("Find By User Name Ends");
				throw new UserException("Email is not available with us");
			}
		} else {
			logger.debug("Find By User Name Ends");
			throw new UserException("Email is required");
		}
	}
	
	/**
	 * Method to update the user after changing the password
	 * @throws UserException 
	 */
	public User updatePassword(String email, String password) throws UserException {
		// TODO: Email triggering
		logger.debug("Update User Starts");
		if (email != null && password != null) {
			User user = userRepository.findByUsername(email).get();
			if (user != null && user.isUserexist()) {
				user.setPassword(passwordEncoder.encode(password));
				user.setUpdateddate(LocalDateTime.now());
				logger.debug("Update User Ends");
				return userRepository.save(user);
			}
			else {
				logger.debug("Update User Ends");
				throw new UserException("User Not available");
			}
		} else {
			logger.debug("Update User Ends");
			throw new UsernameNotFoundException("username not valid");
		}
	}

	/**
	 * 
	 */
	public User changePassword(String email, String oldPassword, String password) throws ChangePasswordException {
		logger.debug("Change Password Starts");
		if(oldPassword.equalsIgnoreCase(password)) {
			logger.debug("Change Password Ends");
			throw new ChangePasswordException("Old password cannot be entered as new password");
		} else {
			User user = userRepository.findByUsername(email).get();
			if (user != null && user.isUserexist()) {
				user.setPassword(passwordEncoder.encode(password));
				user.setUpdateddate(LocalDateTime.now());
				logger.debug("Update User Ends");
				return userRepository.save(user);
			}
		}
		return null;
	}
	
	/**
	 * 
	 */
	public User retrieveUserInformation(String email) throws UserException{
		logger.debug("Retrieve User Starts");
		Optional<User> retrievedUser = userRepository.findByUsername(email);
		if(retrievedUser.isPresent() && retrievedUser.get() != null && retrievedUser.get().isUserexist()) {
			return retrievedUser.get();
		} else {
			throw new UserException("User not available");
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws UserException
	 */
	public User updateUserProfile(User user) throws UserException{
		logger.debug("Update User Profile Starts");
		user.setUpdateddate(LocalDateTime.now());
		logger.debug("Update User Profile Starts");
		return userRepository.save(user);
		
	}

}
