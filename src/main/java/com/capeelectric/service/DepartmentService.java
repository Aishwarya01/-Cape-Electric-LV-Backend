package com.capeelectric.service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;

public interface DepartmentService {
	public void insertDepartment(Department department, Boolean comparingCompanyDepartment) throws CompanyDetailsException;

	public void updateCompany(Department department,Boolean clientNameCompanrison_Department) throws CompanyDetailsException;

	public void deleteDepartment(Integer departmentId) throws CompanyDetailsException;

//	public List<Department> retriveDepartment(Department department) throws CompanyDetailsException;
}
