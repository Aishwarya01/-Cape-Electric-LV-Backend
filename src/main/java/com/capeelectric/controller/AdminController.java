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
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.capeelectric.model.Admin;
import com.capeelectric.model.CustomUserDetails;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.request.ChangePasswordRequest;
import com.capeelectric.response.AuthenticationResponse;
import com.capeelectric.service.AdminControllService;
import com.capeelectric.service.impl.AdminControllerServiceImpl;
import com.capeelectric.service.impl.CustomUserDetailsServiceImpl;

@RestController
@RequestMapping("/api/v2")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private CustomUserDetailsServiceImpl adminDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AdminControllService adminControllService;

	@Autowired
	private AdminControllerServiceImpl adminControllerServiceImpl;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		logger.debug("Create Authenticate Token starts");
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final CustomUserDetails userDetails = adminDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.debug("Create Authenticate Token ends");
		return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getAdmin()));
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
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//		logger.debug("Create Authenticate Token starts");
//		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//
//		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//
//		final String token = jwtTokenUtil.generateToken(userDetails);
//		logger.debug("Create Authenticate Token ends");
//		return ResponseEntity.ok(new AuthenticationResponse(token, userDetails.getUser()));
//	}

	@PostMapping("/registerAdmin")
	public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) throws UserException {
		logger.debug("Add Managemnet starts");
		Admin createdAdmin = adminControllerServiceImpl.saveAdmin(admin);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdAdmin.getAdminId()).toUri();

		logger.debug("Add Managemnet ends");
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/updateAdminDetails")
	public ResponseEntity<String> updateAdminDetails(@RequestBody Admin admin) throws UserException {
		logger.debug("Update management details starts");
		adminControllService.updateAdminDetails(admin);
		logger.debug("Update management details Ends");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/retrieveAdminInformation/{email}")
	public Admin retrieveAdminInformation(@PathVariable String email) throws UserException {
		return adminControllService.retrieveAdminInformation(email);
	}

	@DeleteMapping("/deleteAdmin/{adminId}")
	public ResponseEntity<String> deleteByAdminId(@PathVariable Integer adminId) throws UserException {
		logger.info("called deleteAdmin function adminId: {}", adminId);
		adminControllService.deleteAdmin(adminId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping("/changePassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request)
			throws ChangePasswordException {
		logger.debug("Change Password Starts");
		Admin adminDetails = adminControllService.changePassword(request.getEmail(), request.getOldPassword(),
				request.getPassword());
//		emailService.sendEmail(userDetails.getEmail(), "You have successfully updated your password");
		// awsEmailService.sendEmail(userDetails.getEmail(), "You have successfully
		// updated your password");
		logger.debug("Change Password Ends");
		return new ResponseEntity<String>(adminDetails.getUsername(), HttpStatus.OK);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody AuthenticationRequest request)
			throws UpdatePasswordException {
		logger.debug("Update Password starts");
		Admin admin = adminControllService.updatePassword(request.getEmail(), request.getPassword());
		// awsEmailService.sendEmail(admin.getEmail(), "You have successfully updated
		// your password");
		logger.debug("Update Password ends");
		return new ResponseEntity<String>(admin.getUsername(), HttpStatus.OK);
	}

	@GetMapping("/forgotPassword/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email)
			throws ForgotPasswordException, IOException, MessagingException {
		Admin optionalAdmin = adminControllService.findByAdmin(email);
		return new ResponseEntity<String>(optionalAdmin.getUsername(), HttpStatus.OK);
	}
}
