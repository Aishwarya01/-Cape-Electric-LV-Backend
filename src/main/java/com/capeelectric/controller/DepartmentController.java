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
import com.capeelectric.model.Department;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.service.DepartmentService;
import com.capeelectric.util.ComparingCompanyDetailsUtil;

@RestController()
@RequestMapping("/api/v1")
public class DepartmentController {    // //TODO Byusing DepartmentController to store DepartmentDetails in DB

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private CompanyRepository companyRepository; // TODO delete this line

	@PostMapping("/department")
	public ResponseEntity<String> insertDepartment(@RequestBody Department department) throws CompanyDetailsException {

		logger.debug("called DepartmentController_class insertDepartment function DepartmentclientName: {},DepartmentName :{}",
				department.getClientName(), department.getDepartmentName());

		Boolean clientNameCompanrison_Department = ComparingCompanyDetailsUtil.clientNameCompanrison_Department(department.getClientName(), companyRepository);
		 
		departmentService.insertDepartment(department, clientNameCompanrison_Department);
		
		return new ResponseEntity<String>(department.getClientName() + " " + department.getDepartmentName(),
				HttpStatus.OK);
	}

}