package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;

public interface DepartmentService {
	public Department insertDepartment(Department department, Boolean comparingCompanyDepartment) throws CompanyDetailsException;

	public void updateCompany(Department department) throws CompanyDetailsException;

	public void deleteDepartment(Department department) throws CompanyDetailsException;

	public List<Department> retriveDepartment(Department department) throws CompanyDetailsException;
}
