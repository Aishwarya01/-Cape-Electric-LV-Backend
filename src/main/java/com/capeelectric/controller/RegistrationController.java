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

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.service.impl.AWSEmailService;
import com.capeelectric.util.Constants;
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
		if (createdRegister.getPermission().equalsIgnoreCase("YES")) {
			awsEmailService.sendEmail(register.getUsername(),
					Constants.EMAIL_SUBJECT_REGISTRATION + "\n"
							+ "\n"
							+ (resetUrl.contains("localhost:5000")
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
									: Constants.EMAIL_SUBJECT_URL_AWS)
							+ "/generateOtp" + ";email=" + register.getUsername());
		} else {
			awsEmailService
					.sendEmailToAdmin("Please Approve or Reject the inspector by Logging to " + "Admin Portal for User "
							+ register.getName() + " and Company " + register.getCompanyName() + " with their Email "
							+ register.getUsername() + ". You can login to admin Portal with this link " + "\n"
							+ (resetUrl.contains("localhost:5000")
									? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
									: Constants.EMAIL_SUBJECT_ADMIN_URL_AWS)
							+ "/admin");
		}

		return ResponseEntity.created(uri).build();
	}
	 
	@PostMapping("/addViewerRegistration")
	public ResponseEntity<Void> addViewerRegistration(@RequestBody Register register)
			throws RegistrationException, MessagingException, MalformedURLException, CompanyDetailsException {
		logger.info("called addRegistration function UserName : {}", register.getUsername());
		Register createdRegister = registrationService.addViewerRegistration(register);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdRegister.getRegisterId()).toUri();
		String resetUrl = Utility.getSiteURL(uri.toURL());
		awsEmailService.sendEmail(createdRegister.getUsername(),Constants.EMAIL_SUBJECT_REGISTRATION
				+ "\n" + "\n" 
				+ (resetUrl.contains("localhost:5000")
						? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
								: Constants.EMAIL_SUBJECT_URL_AWS)
				+ "/generateOtp" + ";email=" + register.getUsername());
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/retrieveRegistration/{userName}")
	public Optional<Register> retrieveRegistration(@PathVariable String userName) throws RegistrationException {
		logger.info("called retrieveRegistration function UserName : {}", userName);
		return registrationService.retrieveRegistration(userName);
	}

	@PutMapping("/updateRegistration/{islicenseupdate}")
	public ResponseEntity<String> updateRegistration(@RequestBody Register register,@PathVariable("islicenseupdate") Boolean isLicenseUpdate)
			throws IOException, MessagingException, RegistrationException, CompanyDetailsException {
		logger.debug("called updateRegistration function UserName : {}, IsLicenseUpdate : {}", register.getUsername(),
				isLicenseUpdate);
		registrationService.updateRegistration(register, isLicenseUpdate);
		awsEmailService.sendEmail(register.getUsername(), "You have successfully updated your profile");
		return new ResponseEntity<String>("Successfully Updated Registration", HttpStatus.OK);
	}
	
	@GetMapping("/sendOtp/{userName}/{mobileNumber}")
	public ResponseEntity<Void> sendOtp(@PathVariable String userName,@PathVariable String mobileNumber)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called sendOtp function UserName : {}, MobileNumber : {}", userName, mobileNumber);
		registrationService.sendOtp(userName,mobileNumber);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/newOtpGeneration/{mobileNumber}")
	public String sendNewOtp(@PathVariable String mobileNumber)
			throws IOException, MessagingException, RegistrationException {
		logger.debug("called sendOtp function MobileNumber : {}", mobileNumber);
		return registrationService.sendNewOtp(mobileNumber);
	}
	
	@PutMapping("/updateLicence/{userName}/{numoflicence}")
	public ResponseEntity<String> updateLicence(@PathVariable String userName, @PathVariable String numoflicence)
			throws RegistrationException {
		logger.debug("called updateLicence function UserName : {}", userName);
		registrationService.updateLicence(userName, numoflicence);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
