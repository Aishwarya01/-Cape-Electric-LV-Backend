package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	Boolean flag=false;

	@Override
	public Company addcompany(Company company) throws CompanyDetailsException {

		if (company.getClientName() == null) {
			throw new CompanyDetailsException("ClientName Required");
		} else {
			company.setCreatedDate(LocalDateTime.now());
			company.setUpdatedDate(LocalDateTime.now());
		}

		return companyRepository.save(company);
	}

	@Override
	public void updateCompany(Company company) throws CompanyDetailsException {
		if (company.getUserName() != null) {
			List<Company> companyDetails = companyRepository.findByUserName(company.getUserName());
			for (Company companyUserName : companyDetails) {
				if (companyUserName.getUserName().equalsIgnoreCase(company.getUserName())) {
					companyRepository.save(company);
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new CompanyDetailsException("username not present");
			}

		} else {
			throw new CompanyDetailsException("username required");
		}

	}

	@Override
	public void deleteCompany(String userName) throws CompanyDetailsException {
		if (userName != null) {
			List<Company> companyDetails = companyRepository.findByUserName(userName);
			for (Company company : companyDetails) {
				if (company.getUserName().equalsIgnoreCase(userName)) {
					companyRepository.delete(company);
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new CompanyDetailsException("username not present");
			}

		} else {
			throw new CompanyDetailsException("username required");
		}

	}

	@Override
	public List<Company> retriveCompany(String userName) throws CompanyDetailsException {

		if (userName != null) {
			return companyRepository.findByUserName(userName);

		} else {
			throw new CompanyDetailsException("username required");
		}
	}

}