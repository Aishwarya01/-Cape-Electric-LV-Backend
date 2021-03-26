package com.capeelectric.service;

import org.springframework.stereotype.Service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Department;

@Service
public interface DepartmentService {
	public Department insertDepartment(Department department, Boolean comparingCompanyDepartment) throws UserException;
}
