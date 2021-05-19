package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;
import com.capeelectric.service.impl.DepartmentServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
	
	@MockBean
	private DepartmentServiceImpl departmentServiceImpl;

	@InjectMocks
	private DepartmentController departmentController;
	
	@MockBean
	private CompanyDetailsException companyDetailsException;

	private Department department;

	{
		department = new Department();
		department.setUserName("hasan");
		department.setClientName("HCL");
		department.setCreatedBy("hasan");
		department.setUpdatedBy("hasan");
		department.setDepartmentId(1);
		department.setDepartmentName("Electrical");
	}
	
	@Test
	public void testupdateDepartment() throws  CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = departmentController.updateDepartment(department);;
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}
	@Test
	public void testaddDepartment() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.CREATED);
		ResponseEntity<String> actualResponseEntity = departmentController.addDepartment(department);
		assertEquals(actualResponseEntity, expectedResponseEntity);
}

	@Test
	public void testdeleteDepartment() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = departmentController.deleteDepartment("hasan",1);
		assertEquals(actualResponseEntity, expectedResponseEntity);

	}
	 @Test
	    public void testretriveCompany() throws CompanyDetailsException {
	    	 List<Department> list = new ArrayList<>();
	    	 list.add(department);
	    	 ResponseEntity<List<Department>> expectedResponseEntity =new ResponseEntity<List<Department>>(list, HttpStatus.OK);
		     ResponseEntity<List<Department>> actualResponseEntity = departmentController.retriveDepartment("hasan","HCL");
		     assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());

	    }
}
