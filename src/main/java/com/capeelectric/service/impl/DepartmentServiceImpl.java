package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;

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
	
	Boolean flag = false;
	
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
			throw new CompanyDetailsException("given_clientName not present in company");
		} else {
			department.setCreatedDate(LocalDateTime.now());
			department.setUpdatedDate(LocalDateTime.now());
		}

		return departmentRepository.save(department);
	}

	@Override
	public void updateCompany(Department department) throws CompanyDetailsException {
		if (department.getDepartmentName() != null && department.getClientName() != null
				&& department.getUserName() != null) {
 			List<Department> username = departmentRepository.findByUserNameAndClientName(department.getUserName(),department.getClientName());

			for (Department departmentRepo : username) {
				if (departmentRepo.getDepartmentId().equals(department.getDepartmentId())) {
					if (departmentRepo.getUserName().equalsIgnoreCase(department.getUserName())) {
						if (departmentRepo.getClientName().equalsIgnoreCase(department.getClientName())) {
							departmentRepository.save(department);
							flag = true;
							break;
						} 
					} 
				}
				if (!flag) {
					throw new CompanyDetailsException("invalid details");
				}
			}
	
		} else {
			throw new CompanyDetailsException("invalid required");
		}

	}

	@Override
	public void deleteDepartment(Department department) throws CompanyDetailsException {
		if (department.getDepartmentName() != null && department.getClientName() != null
				&& department.getUserName() != null) {
			List<Department> username = departmentRepository.findByUserNameAndClientName(department.getUserName(),department.getClientName());

			for (Department departmentRepo : username) {
				if (departmentRepo.getDepartmentId().equals(department.getDepartmentId())) {
					if (departmentRepo.getUserName().equalsIgnoreCase(department.getUserName())) {
						if (departmentRepo.getClientName().equalsIgnoreCase(department.getClientName())) {
							departmentRepository.delete(department);
							flag = true;
							break;
						}
					}
				}
			}
			if (!flag) {
				throw new CompanyDetailsException("invalid details");
			}

		} else {
			throw new CompanyDetailsException("Department ClientName Username required");
		}
	}

	@Override
	public List<Department> retriveDepartment(Department department) throws CompanyDetailsException {
		if (department.getUserName() != null && department.getClientName() != null) {
			return departmentRepository.findByUserNameAndClientName(department.getUserName(),department.getClientName());
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}
}
