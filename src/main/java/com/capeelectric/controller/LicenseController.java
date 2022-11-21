package com.capeelectric.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.ViewerRegister;
import com.capeelectric.model.licence.LpsRegister;
import com.capeelectric.model.licence.LvRegister;
import com.capeelectric.service.LicenseService;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.Utility;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v2")
public class LicenseController {

	private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

	@Autowired
	private LicenseService licenseService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Value("${app.web.domain}")
	private String webUrl;
	
	@PostMapping("/addViewerRegistrationLicense")
	public ResponseEntity<Void> addRegistration(@RequestBody ViewerRegister viewerRegister)
			throws RegistrationException, MessagingException, MalformedURLException, CompanyDetailsException, URISyntaxException {
		logger.debug("called addRegistration function UserName : {}", viewerRegister.getUsername());
		
		ViewerRegister createdRegister = licenseService.addViewerRegistration(viewerRegister);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdRegister.getRegisterId()).toUri();
		String resetUrl = Utility.getSiteURL(uri.toURL());
//		registrationService.sendEmailForOTPGeneration(createdRegister.getUsername(), Constants.EMAIL_SUBJECT_REGISTRATION + "\n" + "\n"
//				+ (resetUrl.contains("localhost:5000")
//						? resetUrl.replace("http://localhost:5000", "http://localhost:4200")
//						: "https://www."+webUrl)
//				+ "/generateOtp" + ";email=" + viewerRegister.getUsername());
		logger.debug("AwsEmailService Successfully call Ended");
		return ResponseEntity.created(uri).build();
	}
	
//	@GetMapping("/retrieveLvRegister/{userName}")
//	public Optional<LvRegister> retrieveLvRegister(@PathVariable String userName) throws RegistrationException {
//		logger.debug("called retrieveUserName function UserName : {}", userName);
//		return licenseService.retrieveLvRegister(userName);
//	}
	
//	@GetMapping("/retrieveLpsRegister/{userName}")
//	public Optional<LpsRegister> retrieveLpsRegister(@PathVariable String userName) throws RegistrationException {
//		logger.debug("called retrieveUserName function UserName : {}", userName);
//		return licenseService.retrieveLpsRegister(userName);
//	}
}
