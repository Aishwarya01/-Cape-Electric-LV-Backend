package com.capeelectric.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capeelectric.model.CustomUserDetails;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);
	@Autowired
	private UserRepository usersRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Load User By UserName starts");
		User user = usersRepository.findByUsername(username).get();
		CustomUserDetails userDetails = null;
		if (user != null) {
			userDetails = new CustomUserDetails(user);
		} else {
			logger.debug("Load User By UserName ends");
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}
		logger.debug("Load User By UserName ends");
		return userDetails;
	}
}