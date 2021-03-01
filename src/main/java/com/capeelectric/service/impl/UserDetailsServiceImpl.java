package com.capeelectric.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public User saveUser(User user) throws UserException {
		Optional<User> createdUser = userRepository.findByUserName(user.getUserName());
		if(createdUser.isPresent() && createdUser.get() != null && createdUser.get().isUserExist()) {
			throw new UserException("User already available");
		} else {
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			user.setUserExist(true);
			user.setCreationDate(new Date());
			user.setUpdatedDate(new Date());
			return userRepository.save(user);
		}
		
	}
}
