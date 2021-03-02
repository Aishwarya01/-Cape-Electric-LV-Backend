package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/**
	 * Method to save the user during the registration
	 */
	public User saveUser(User user) throws UserException {
		Optional<User> createdUser = userRepository.findByUserName(user.getUserName());
		if(createdUser.isPresent() && createdUser.get() != null && createdUser.get().isUserExist()) {
			throw new UserException("User already available");
		} else {
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			user.setUserExist(true);
			user.setCreationDate(LocalDateTime.now ());
			user.setUpdatedDate(LocalDateTime.now ());
			return userRepository.save(user);
		}
	}
	
	/**
	 * Method to retrieve the user
	 */
	public ResponseEntity<String> findByUserName(String email) {
		// TODO: Email triggering
		if (email != null) {
			User user = userRepository.findByUserName(email).get();
			if (user != null) {
				return new ResponseEntity<String>(user.getUserName(), HttpStatus.OK);
			} else {
				throw new UsernameNotFoundException("username not valid");
			}
		} else {
			throw new UsernameNotFoundException("User name required");
		}
	}
	
	/**
	 * Method to update the user after changing the password
	 * @throws UserException 
	 */
	public User updateUser(String email, String password) throws UserException {
		// TODO: Email triggering
		if (email != null && password != null) {
			User user = userRepository.findByUserName(email).get();
			if (user != null && user.isUserExist()) {
				user.setPassword(passwordEncoder.encode(password));
				user.setUpdatedDate(LocalDateTime.now());
				return userRepository.save(user);
			}
			else {
				throw new UserException("User Not available");
			}
		} else {
			throw new UsernameNotFoundException("username not valid");
		}
	}
}
