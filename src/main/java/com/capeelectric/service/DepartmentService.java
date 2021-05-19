package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;

public interface DepartmentService {
	public void addDepartment(Department department) throws CompanyDetailsException;

	public void updateDepartment(Department department) throws CompanyDetailsException;

	public void deleteDepartment(Integer departmentId) throws CompanyDetailsException;

	public List<Department> retriveDepartment(String userName,String clientName) throws CompanyDetailsException;
}
