package com.capeelectric.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserException {
		User createdUser = userService.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/authenticate")
	public CustomUserDetails fetchUser(@RequestBody AuthenticationRequest request) {
		return userDetailsService.loadUserByUsername(request.getEmail());
	}
	
	@GetMapping("/forgotPassword/{userName}")
	public ResponseEntity<String> forgetPassword(@PathVariable String userName) {
 		return userService.findByUserName(userName);

	}
}
