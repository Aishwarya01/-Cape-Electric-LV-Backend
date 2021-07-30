package com.capeelectric.controller;

import java.io.IOException;
import java.net.URI;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.config.JwtTokenUtil;
import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.CustomUserDetails;
import com.capeelectric.model.User;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.request.ChangePasswordRequest;
import com.capeelectric.request.UpdatePasswordRequest;
import com.capeelectric.response.AuthenticationResponse;
import com.capeelectric.service.impl.AWSEmailService;
import com.capeelectric.service.impl.CustomUserDetailsServiceImpl;
import com.capeelectric.service.impl.UserDetailsServiceImpl;
import com.capeelectric.util.Utility;

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
	private CustomUserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AWSEmailService awsEmailService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/registerUser")
	public ResponseEntity<Void> addUser(@RequestBody User user) throws UserException, IOException, MessagingException {
		logger.debug("Add User starts");
		User createdUser = userService.saveUser(user);
		awsEmailService.sendEmail(user.getEmail(), "You have been successfully Registered with Rush for Safety App. You may need to wait for 2hrs for getting approved from Admin.");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		logger.debug("Add User ends");
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		logger.debug("Create Authenticate Token starts");
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.debug("Create Authenticate Token ends");
		return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUser()));
	}
	
	@GetMapping("/forgotPassword/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email) throws ForgotPasswordException, IOException, MessagingException, UserException{
 		User optionalUser =  userService.findByUserName(email);
 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(optionalUser.getId()).toUri();
 		String resetUrl = Utility.getSiteURL(uri.toURL());
 		Integer generatedOTP = Utility.generateOTP(email);
 		awsEmailService.sendEmail(email, "You can update the password with this link"+ "\n"
 					+(resetUrl.contains("localhost:5000") ? resetUrl.replace("http://localhost:5000", "http://localhost:4200") : "https://www.rushforsafety.com") + "/updatepassword" + ";email="+email
 					 + " and OTP is "+generatedOTP);
 		optionalUser.setOtp(generatedOTP);
 		userService.updateUserProfile(optionalUser);
 		return new ResponseEntity<String>(optionalUser.getUsername(), HttpStatus.OK);
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody UpdatePasswordRequest request) throws UpdatePasswordException, IOException, MessagingException{
		logger.debug("Update Password starts");
		User user  = userService.updatePassword(request.getEmail(), request.getPassword(), request.getOtp());
		awsEmailService.sendEmail(user.getEmail(), "You have successfully updated your password");
		logger.debug("Update Password ends");
		return new ResponseEntity<String>(user.getUsername(), HttpStatus.OK);
	}
	
	@PutMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) throws ChangePasswordException, IOException, MessagingException{
		logger.debug("Change Password Starts");
		User userDetails = userService.changePassword(request.getEmail(), request.getOldPassword(), request.getPassword());
		awsEmailService.sendEmail(userDetails.getEmail(), "You have successfully updated your password");
		logger.debug("Change Password Ends");
		return new ResponseEntity<String>(userDetails.getUsername(), HttpStatus.OK);
	}
	
	@GetMapping("/retrieveUserInformation/{email}")
	public User retrieveUserInformation(@PathVariable String email) throws UserException {
		return userService.retrieveUserInformation(email);
	}
	
	@PutMapping("/updateUserProfile")
	public ResponseEntity<String> updateUserProfile(@RequestBody User user) throws IOException, MessagingException{
		logger.debug("Update User Profile starts");
		User updatedUser = userService.updateUserProfile(user);
		awsEmailService.sendEmail(user.getEmail(), "You have successfully updated your profile");
		logger.debug("Update Password ends");
		return new ResponseEntity<String>(updatedUser.getEmail(), HttpStatus.OK);
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
