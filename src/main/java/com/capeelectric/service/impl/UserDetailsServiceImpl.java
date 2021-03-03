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
import com.capeelectric.model.CustomUserDetails;
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
	public ResponseEntity<String> findByUserName(String email) throws UserException{
		// TODO: Email triggering
		if (email != null) {
			Optional<User> optionalUser = userRepository.findByUserName(email);
			if (optionalUser != null && optionalUser.isPresent() && optionalUser.get()!= null) {
				return new ResponseEntity<String>(optionalUser.get().getUserName(), HttpStatus.OK);
			} else {
				throw new UserException("Email is not available with us");
			}
		} else {
			throw new UserException("Email is required");
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

	@Override
	public CustomUserDetails loadUserInformation(CustomUserDetails userDetails, String password) throws UserException {
		if(passwordEncoder.matches(password, userDetails.getPassword())) {
			return userDetails;
		} else {
			throw new UserException("Password is not matching");
		}
	}
}
