package com.capeelectric.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.config.JwtTokenUtil;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.response.AuthenticationResponse;
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
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
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
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		logger.debug("Create Authenticate Token starts");
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.debug("Create Authenticate Token ends");
		return ResponseEntity.ok(new AuthenticationResponse(token));
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
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
