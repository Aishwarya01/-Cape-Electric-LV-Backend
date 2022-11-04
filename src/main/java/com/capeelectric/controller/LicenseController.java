package com.capeelectric.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.licence.LvRegister;
import com.capeelectric.service.LicenseService;

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
	
	@GetMapping("/retrieveRegister/{userName}")
	public Optional<LvRegister> retrieveLvRegister(@PathVariable String userName) throws RegistrationException {
		logger.debug("called retrieveUserName function UserName : {}", userName);
		return licenseService.retrieveLvRegister(userName);
	}
}
