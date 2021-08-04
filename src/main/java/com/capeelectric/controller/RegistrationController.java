package com.capeelectric.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

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

import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.request.RegisterPermissionRequest;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.service.impl.AWSEmailService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v2")
public class RegistrationController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	private AWSEmailService awsEmailService;

	@Autowired
	private RegistrationService registrationService;

	@PostMapping("/addRegistration")
	public ResponseEntity<Void> addRegistration(@RequestBody Register register)
			throws RegistrationException, MessagingException {
		logger.info("called addRegistration function UserName : {}", register.getUsername());
		Register registrationCreated = registrationService.addRegistration(register);

		awsEmailService.sendEmail(register.getUsername(),
				"You have been successfully Registered with Rush for Safety App. You may need to wait for 2hrs for getting approved from Admin.");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(registrationCreated.getRegisterId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/retrieveRegistration/{userName}")
	public Optional<Register> retrieveRegistration(@PathVariable String userName) throws RegistrationException {
		logger.info("called retrieveRegistration function UserName : {}", userName);
		return registrationService.retrieveRegistration(userName);
	}

	@GetMapping("/retrieveAllRegistration")
	public List<Register> retrieveAllRegistration() throws RegistrationException {
		logger.info("called retrieveAllRegistration function");
		return registrationService.retrieveAllRegistration();
	}

	@PutMapping("/updateRegistration")
	public ResponseEntity<String> updateRegistration(@RequestBody Register register)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called updateRegistration function UserName : {}", register.getUsername());
		registrationService.updateRegistration(register);
		awsEmailService.sendEmail(register.getUsername(), "You have successfully updated your profile");
		return new ResponseEntity<String>("Successfully Updated Registration", HttpStatus.OK);
	}

	@PutMapping("/updatePermission")
	public ResponseEntity<String> updatePermission(@RequestBody RegisterPermissionRequest registerPermissionRequest)
			throws RegistrationException, RegisterPermissionRequestException, MessagingException {
		logger.info("called updatePermission function AdminUserName : {}", registerPermissionRequest.getAdminUserName());
		Register register = registrationService.updatePermission(registerPermissionRequest);
		if (register != null && register.getPermission().equalsIgnoreCase("YES")) {
			awsEmailService.sendEmail(register.getUsername(),
					"Your Registration has been Approved Successfully, Now You Can Login Rush for Safety App");
		} else {
			awsEmailService.sendEmail(register.getUsername(),
					"Your Registration has not Approved, So You Can't Access Rush for Safety App");
		}
		return new ResponseEntity<String>("Successfully Updated RegisterPermission", HttpStatus.OK);
	}

}
