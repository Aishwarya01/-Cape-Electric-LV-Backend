package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.service.CompanyService;

@RestController()
@RequestMapping("/api/v1")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@PostMapping("/addcompany")
	public ResponseEntity<String> addcompany(@RequestBody Company company) throws CompanyDetailsException {
		logger.info("called InsertCompany function clientName: {}", company.getClientName());
		companyService.addcompany(company);
		return new ResponseEntity<String>(company.getClientName(), HttpStatus.OK);
	}

	@PostMapping("/updatecompany")
	public ResponseEntity<String> updateCompany(@RequestBody Company company) throws CompanyDetailsException {
		logger.info("called updateCompany function clientName: {}", company.getUserName());
		companyService.updateCompany(company);
		return new ResponseEntity<String>("Record Updated", HttpStatus.OK);
	}

	@PostMapping("/deletecompany/{userName}")
	public ResponseEntity<String> deleteCompany(@PathVariable String userName) throws CompanyDetailsException {
		logger.info("called updateCompany function clientName: {}", userName);
		companyService.deleteCompany(userName);
		return new ResponseEntity<String>("Record Deleted", HttpStatus.OK);
	}

	@GetMapping("/retriveCompany/{userName}")
	public ResponseEntity<Company> retriveCompany(@PathVariable String userName) throws CompanyDetailsException {
		logger.info("called updateCompany function clientName: {}", userName);
		Company company = companyService.retriveCompany(userName);
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}
}