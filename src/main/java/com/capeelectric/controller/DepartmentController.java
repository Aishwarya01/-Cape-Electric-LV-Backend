package com.capeelectric.controller;

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

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.service.DepartmentService;
import com.capeelectric.util.ComparingCompanyDetailsUtil;

@RestController()
@RequestMapping("/api/v1")
public class DepartmentController {     

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CompanyRepository companyRepository; // TODO delete this line

	@PostMapping("/addDepartment")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) throws CompanyDetailsException {

		logger.debug(
				"called DepartmentController_class insertDepartment function DepartmentclientName: {},DepartmentName :{}",
				department.getClientName(), department.getDepartmentName());

		Boolean clientNameCompanrison_Department = ComparingCompanyDetailsUtil
				.clientNameCompanrison_Department(department.getClientName(), companyRepository);

		departmentService.insertDepartment(department, clientNameCompanrison_Department);

		return new ResponseEntity<String>("successfully added Department", HttpStatus.OK);
	}

	@PutMapping("/updateDepartment")
	public ResponseEntity<String> updateDepartment(@RequestBody Department department) throws CompanyDetailsException {
		logger.info("called updateDepartment function clientName: {},DepartmentName :{}", department.getUserName(),
				department.getDepartmentName());
		Boolean clientNameCompanrison_Department = ComparingCompanyDetailsUtil
				.clientNameCompanrison_Department(department.getClientName(), companyRepository);
		departmentService.updateCompany(department,clientNameCompanrison_Department);
		return new ResponseEntity<String>("Department Updated", HttpStatus.OK);
	}

	@GetMapping("/deleteDepartment/{departmentId}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Integer departmentId) throws CompanyDetailsException {
		logger.info("called deleteDepartment function departmentId: {}", departmentId);
		departmentService.deleteDepartment(departmentId);
		return new ResponseEntity<String>("Department deleted", HttpStatus.OK);
	}

	/*
	 * @PostMapping("/retriveDepartment") public ResponseEntity<List>
	 * retriveDepartment(@RequestBody Department department) throws
	 * CompanyDetailsException { logger.
	 * info("called retriveDepartment function clientName: {},DepartmentName :{}",
	 * department.getUserName(), department.getClientName()); return new
	 * ResponseEntity<List>(departmentService.retriveDepartment(department),
	 * HttpStatus.OK); }
	 */
}