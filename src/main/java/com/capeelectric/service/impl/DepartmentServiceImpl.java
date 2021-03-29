package com.capeelectric.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department insertDepartment(Department department, Boolean clientNameCompanrison_Department)
			throws CompanyDetailsException {

		logger.debug("called DepartmentServiceImpl_class insertDepartment_function");

		if (department.getClientName() == null) {
			throw new CompanyDetailsException("ClientName Required");
		}
		else if (department.getDepartmentName() == null) {
			throw new CompanyDetailsException("DepartmentName Required");
		}
		else if (clientNameCompanrison_Department == false) {
			throw new CompanyDetailsException("Department clientName not avilable in company");
		} else {
			department.setCreatedDate(LocalDateTime.now());
			department.setUpdatedDate(LocalDateTime.now());
		}

		return departmentRepository.save(department);
	}

}
