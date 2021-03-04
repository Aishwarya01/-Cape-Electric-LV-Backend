package com.capeelectric.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.CustomUserDetails;
import com.capeelectric.model.User;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.service.impl.CustomUserDetailsService;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<Void> addUser(@RequestBody User user) throws UserException {
		logger.debug("Add User starts");
		User createdUser = userService.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		logger.debug("Add User ends");
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/authenticate")
	public CustomUserDetails fetchUser(@RequestBody AuthenticationRequest request) throws UserException {
		return userService.loadUserInformation(userDetailsService.loadUserByUsername(request.getEmail()), request.getPassword());
	}
	
	@GetMapping("/forgotPassword/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) throws UserException{
 		return userService.findByUserName(email);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updateUser(@RequestBody AuthenticationRequest request) throws UserException{
		logger.debug("Update User starts");
		User user  = userService.updateUser(request.getEmail(), request.getPassword());
		logger.debug("Update User ends");
		return new ResponseEntity<String>(user.getUserName(), HttpStatus.OK);
	}
}
