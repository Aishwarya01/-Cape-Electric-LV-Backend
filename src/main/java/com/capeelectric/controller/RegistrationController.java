package com.capeelectric.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
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

import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.service.impl.AWSEmailService;
import com.capeelectric.util.Utility;

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
			throws RegistrationException, MessagingException, MalformedURLException {
		logger.info("called addRegistration function UserName : {}", register.getUsername());
		Register createdRegister = registrationService.addRegistration(register);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdRegister.getRegisterId()).toUri();
		String resetUrl = Utility.getSiteURL(uri.toURL());
		awsEmailService.sendEmail(createdRegister.getUsername(),
				"You have been successfully Registered with Rush for Safety App. You may need to wait for 2hrs for getting approved from Admin."
						+ "\n" + "\n" + "You can create the password with this link " + "\n"
						+ (resetUrl.contains("localhost:5000")
								? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
								: "https://www.rushforsafety.com")
						+ "/createPassword" + ";email=" + createdRegister.getUsername());
		awsEmailService.sendMultiplePerson("Please Approve or Reject the inspector by Logging to Admin Portal");
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/retrieveRegistration/{userName}")
	public Optional<Register> retrieveRegistration(@PathVariable String userName) throws RegistrationException {
		logger.info("called retrieveRegistration function UserName : {}", userName);
		return registrationService.retrieveRegistration(userName);
	}

	@PutMapping("/updateRegistration")
	public ResponseEntity<String> updateRegistration(@RequestBody Register register)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called updateRegistration function UserName : {}", register.getUsername());
		registrationService.updateRegistration(register);
		awsEmailService.sendEmail(register.getUsername(), "You have successfully updated your profile");
		return new ResponseEntity<String>("Successfully Updated Registration", HttpStatus.OK);
	}
	
	@GetMapping("/resendOtp/{mobileNumber}")
	public ResponseEntity<String> resendOtp(@PathVariable String mobileNumber)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called resendOtp function mobileNumber : {}", mobileNumber);
		return new ResponseEntity<String>(registrationService.resendOtp(mobileNumber), HttpStatus.OK);
	}
}
