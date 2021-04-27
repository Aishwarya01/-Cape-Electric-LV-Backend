package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;
import com.capeelectric.service.DepartmentService;
 
@RestController()
@RequestMapping("/api/v1")
public class DepartmentController {     

	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@PostMapping("/addDepartment")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) throws CompanyDetailsException {

		logger.info("called addDepartment function clientName: {},DepartmentName :{}", department.getUserName(),
				department.getDepartmentName());
		departmentService.addDepartment(department);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("/updateDepartment")
	public ResponseEntity<String> updateDepartment(@RequestBody Department department)
			throws CompanyDetailsException {
		logger.info("called updateDepartment function clientName: {},DepartmentName :{}", department.getUserName(),
				department.getDepartmentName());
		departmentService.updateDepartment(department);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping("/deleteDepartment/{userName}/{departmentId}")
	public ResponseEntity<String> deleteDepartment(@PathVariable String userName, @PathVariable Integer departmentId)
			throws CompanyDetailsException {
		logger.info("called deleteDepartment function departmentId: {}", departmentId);
		departmentService.deleteDepartment(departmentId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/retriveDepartment/{userName}/{clientName}")
	public ResponseEntity<List<Department>> retriveDepartment(@PathVariable String userName,@PathVariable String clientName)
			throws CompanyDetailsException {
		logger.info("called retriveDepartment function userName{} ,clientName :{}",userName, clientName);
		return new ResponseEntity<List<Department>>(departmentService.retriveDepartment(userName,clientName), HttpStatus.OK);
	}

}