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

import com.capeelectric.exception.CompanyException;
import com.capeelectric.model.Company;
import com.capeelectric.service.CompanyDetailsService;

@RestController()
@RequestMapping("/api/v1")
public class CompanyDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyDetailsController.class);

	@Autowired
	private CompanyDetailsService companyDetailsService;

	@PostMapping("/clientname")
	public ResponseEntity<String> insertCompany(@RequestBody Company company) throws CompanyException {
		logger.debug(" InsertCompany() client name adding starts");
		companyDetailsService.insertCompany(company);
		logger.debug(" InsertCompany() client name adding end");
		return new ResponseEntity<String>(company.getClientName(), HttpStatus.OK);
	}

}
