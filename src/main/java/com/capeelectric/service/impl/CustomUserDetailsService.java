package com.capeelectric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capeelectric.model.CustomUserDetails;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;
    
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	User user = usersRepository.findByUserName(username).get();
		CustomUserDetails userDetails = null;
		if (user != null) {
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}
		return userDetails;
    }
    
	public  ResponseEntity<String> findByName(String userName) {
		//TODO: Email triggering				
				if ( userName != null) {
					  User user = usersRepository.findByUserName(userName).get();					 
					if (user != null) {
						return  ResponseEntity.ok(user.getUserName());
					} else {
						throw new UsernameNotFoundException("username not valid");
						}
					} else {
						throw new UsernameNotFoundException("User name required");
					}          
				
			}
}