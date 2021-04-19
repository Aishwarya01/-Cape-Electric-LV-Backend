package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Department;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.service.DepartmentService;
import com.capeelectric.util.ComparingCompanyDetailsUtil;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public void insertDepartment(Department department, Boolean clientNameCompanrison_Department)
			throws CompanyDetailsException {
		Boolean flag = false;
		if (department.getDepartmentName() != null && department.getClientName() != null) {

			List<Department> repoDepartments = departmentRepository
					.findByUserNameAndClientName(department.getUserName(), department.getClientName());

			for (Department departments : repoDepartments) {
				if (departments.getDepartmentName().equalsIgnoreCase(department.getDepartmentName())) {
					flag = true;
					throw new CompanyDetailsException("DepartmentName already present");
				} else {
					if (clientNameCompanrison_Department == false) {
						flag = true;
						throw new CompanyDetailsException("given_clientName not present in company");
					}
				}

			}
			if (!flag) {
				department.setCreatedDate(LocalDateTime.now());
				department.setUpdatedDate(LocalDateTime.now());
				departmentRepository.save(department);
			}
		}

		else {
			throw new CompanyDetailsException("invalid inputs");
		}

	}

	@Override
	public void updateCompany(Department department, Boolean clientNameCompanrison_Department)
			throws CompanyDetailsException {
		if (department.getClientName() != null && department.getUserName() != null
				&& department.getDepartmentId() != null ) {
			if (clientNameCompanrison_Department) {
				Optional<Department> departmentRepo = departmentRepository.findById(department.getDepartmentId());

				if (departmentRepo.get().getDepartmentId().equals(department.getDepartmentId())) {
					department.setUpdatedDate(LocalDateTime.now());
					departmentRepository.save(department);
				}

				else {
					throw new CompanyDetailsException("department not present");

				}

			} else {
				throw new CompanyDetailsException("given_clientName not present in company");
			}

		} else {
			throw new CompanyDetailsException("department details invalid ");
		}

	}

	@Override
	public void deleteDepartment(Integer departmentId) throws CompanyDetailsException {
		if (departmentId != null && departmentId != 0) {
			Optional<Department> username = departmentRepository.findById(departmentId);

			if (username.get().getDepartmentId() == departmentId) {
				departmentRepository.deleteById(departmentId);
			} else {
				throw new CompanyDetailsException("invalid details");
			}

		} else {
			throw new CompanyDetailsException("invaild inputs");
		}
	}

	/*
	 * @Override public List<Department> retriveDepartment(Department department)
	 * throws CompanyDetailsException { if (department.getUserName() != null &&
	 * department.getClientName() != null) { return
	 * departmentRepository.findByUserNameAndClientName(department.getUserName(),
	 * department.getClientName()); } else { throw new
	 * CompanyDetailsException("invalid inputs"); } }
	 */
}
