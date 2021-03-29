package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.service.CompanyService;

@RestController()
@RequestMapping("/api/v1")
public class CompanyController {   //TODO Byusing CompanyController to store companyDetails in DB

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@PostMapping("/company")
	public ResponseEntity<String> insertCompany(@RequestBody Company company) throws CompanyDetailsException {
		logger.info("called InsertCompany function clientName: {}", company.getClientName());
		companyService.insertCompany(company);
		return new ResponseEntity<String>(company.getClientName(), HttpStatus.OK);
	}

}
