package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/adddepartment")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) throws CompanyDetailsException {

		logger.debug(
				"called DepartmentController_class insertDepartment function DepartmentclientName: {},DepartmentName :{}",
				department.getClientName(), department.getDepartmentName());

		Boolean clientNameCompanrison_Department = ComparingCompanyDetailsUtil
				.clientNameCompanrison_Department(department.getClientName(), companyRepository);

		departmentService.insertDepartment(department, clientNameCompanrison_Department);

		return new ResponseEntity<String>("Record inserted", HttpStatus.OK);
	}

	@PutMapping("/updatedepartment")
	public ResponseEntity<String> updateDepartment(@RequestBody Department department) throws CompanyDetailsException {
		logger.info("called updateDepartment function clientName: {},DepartmentName :{}", department.getUserName(),
				department.getDepartmentName());
		departmentService.updateCompany(department);
		return new ResponseEntity<String>("Record Updated", HttpStatus.OK);
	}

	@PostMapping("/deletedepartment")
	public ResponseEntity<String> deleteDepartment(@RequestBody Department department) throws CompanyDetailsException {
		logger.info("called deleteDepartment function clientName: {},DepartmentName :{}", department.getUserName(),
				department.getClientName());
		departmentService.deleteDepartment(department);
		return new ResponseEntity<String>("Record deleted", HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/retrivedepartment")
	public ResponseEntity<List> retriveDepartment(@RequestBody Department department) throws CompanyDetailsException {
		logger.info("called retriveDepartment function clientName: {},DepartmentName :{}", department.getUserName(),
				department.getClientName());
		return new ResponseEntity<List>(departmentService.retriveDepartment(department), HttpStatus.OK);
	}
}