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

import com.capeelectric.exception.UserException;
import com.capeelectric.model.CustomUserDetails;
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
		Optional<User> createdUser = userRepository.findByUserName(user.getUsername());
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
			Optional<User> optionalUser = userRepository.findByUserName(email);
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
	public User updateUser(String email, String password) throws UserException {
		// TODO: Email triggering
		logger.debug("Update User Starts");
		if (email != null && password != null) {
			User user = userRepository.findByUserName(email).get();
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

	@Override
	public CustomUserDetails loadUserInformation(CustomUserDetails userDetails, String password) throws UserException {
		logger.debug("Load User Information Starts");
		if(passwordEncoder.matches(password, userDetails.getPassword())) {
			logger.debug("Load User Information Ends");
			return userDetails;
		} else {
			logger.debug("Load User Information Ends");
			throw new UserException("Password is not matching");
		}
	}
}
