package com.capeelectric.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Department;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department insertDepartment(Department department, Boolean comparingCompanyDepartmentClientName)
			throws UserException {

		logger.debug("called DepartmentServiceImpl_class insertDepartment_function");

		if (department.getClientName() == null) {
			throw new UserException("ClientName Required");
		}
		if (department.getDepartmentName() == null) {
			throw new UserException("DepartmentName Required");
		}
		if (comparingCompanyDepartmentClientName == false) {
			throw new UserException("Department clientName not avilable in company");
		} else {
			department.setCreatedDate(LocalDateTime.now());
			department.setUpdatedDate(LocalDateTime.now());
		}

		return departmentRepository.save(department);
	}

}
