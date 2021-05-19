package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.model.Department;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
 
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * @param Department
	 * addDepartment method to comparing clientName, comparing department_Name
	 */
	@Override
	public void addDepartment(Department department) throws CompanyDetailsException {
		int count = 0;																	 

		if (department.getDepartmentName() != null && !department.getClientName().isEmpty() && department.getClientName() != null) {
			Optional<Company> companyRepo = companyRepository.findByUserNameAndClientName(department.getUserName(),
					department.getClientName());
			if (companyRepo.isPresent() && companyRepo.get() != null
					&& companyRepo.get().getClientName().equalsIgnoreCase(department.getClientName())) {
				if (departmentRepository.findByClientNameAndDepartmentName(department.getClientName(),
						department.getDepartmentName()) != null) {
					throw new CompanyDetailsException(
							department.getDepartmentName() + " DepartmentName already present :"+department.getClientName());
				} else {
					department.setCompany(companyRepo.get());
					department.setDepartmentCd(department.getDepartmentName().substring(0, 3).concat("_0")+(count+1)); 
					department.setCreatedDate(LocalDateTime.now());
					department.setUpdatedDate(LocalDateTime.now());
					department.setCreatedBy(generateFullName(department.getUserName()));
					department.setUpdatedBy(generateFullName(department.getUserName()));
					departmentRepository.save(department);
				}
			} else {
				throw new CompanyDetailsException(
						department.getClientName() + " :  clientname not present in company");
			}
		}
		else {
			throw new CompanyDetailsException("invalid inputs");
		}

	}
	
	/*
	 * @param Department
	 * updateDepartment method to comparing clientName clientName,comparing departmentName
	 * 
	 */
	@Override
	public void updateDepartment(Department department) throws CompanyDetailsException {
		int count = 0;
		Boolean flag = true;

		if (department.getClientName() != null && department.getUserName() != null
				&& department.getDepartmentId() != null && department.getDepartmentName() != null) {
			Optional<Company> companyRepo = companyRepository.findByUserNameAndClientName(department.getUserName(),
					department.getClientName());

			if (companyRepo.isPresent() && companyRepo.get() != null
					&& companyRepo.get().getClientName().equalsIgnoreCase(department.getClientName())) {

				List<Department> deptRepo = departmentRepository.findByClientName(department.getClientName());

				for (Department clientName : deptRepo) {
					if (clientName.getDepartmentName().equalsIgnoreCase(department.getDepartmentName())
							&& clientName.getDepartmentId().equals(department.getDepartmentId())) {
						department.setDepartmentCd(
								department.getDepartmentName().substring(0, 3).concat("_0") + (count + 1));
						department.setUpdatedDate(LocalDateTime.now());
						department.setUpdatedBy(generateFullName(department.getUserName()));
						departmentRepository.save(department);
						flag = false;
						break;
					}
					if (clientName.getDepartmentName().equalsIgnoreCase(department.getDepartmentName())) {
						throw new CompanyDetailsException(
								"DepartmentName Already present user :" + clientName.getClientName());
					}
				}

			} else {
				throw new CompanyDetailsException("Company not present user :" + department.getUserName());
			}

			if (flag) {
				department.setUpdatedDate(LocalDateTime.now());
				department.setUpdatedBy(generateFullName(department.getUserName()));
				departmentRepository.save(department);
			}

		} else {
			throw new CompanyDetailsException("Inavlid inputs");
		}

	}
 
	/*
	 * @param departmentId
	 * deleteDepartment method comparing departmentId and input_departmentId 
	 * if matched department will be deleted
	 * 
	 */
	@Override
	public void deleteDepartment(Integer departmentId) throws CompanyDetailsException {
		if (departmentId != null && departmentId != 0) {
			Optional<Department> department = departmentRepository.findById(departmentId);

			if (department.isPresent() && department.get().getDepartmentId() == departmentId) {
				departmentRepository.deleteById(departmentId);
			} else {
				throw new CompanyDetailsException(departmentId + " : department ID not present");
			}

		} else {
			throw new CompanyDetailsException("invaild inputs");
		}
	}

	/*
	 * @param clientName
	 * fetching data from DB
	 */
	@Override
	public List<Department> retriveDepartment(String userName, String clientName) throws CompanyDetailsException {
		if (clientName != null && !clientName.isEmpty() && userName !=null && !userName.isEmpty()) {
			return departmentRepository.findByUserNameAndClientName(userName,clientName);
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}
	
	
	private String generateFullName(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent() && user.get() != null)
			return user.get().getFirstname() + " " + user.get().getLastname();
		return "";
	}
}
