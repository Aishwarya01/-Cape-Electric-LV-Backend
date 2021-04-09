package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.controller.CompanyController;
import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyRepository companyRepository;

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
					break;
				} else {
					throw new CompanyDetailsException("username not present");
				}
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
					break;
				} else {
					throw new CompanyDetailsException("username not present");
				}
			}

		} else {
			throw new CompanyDetailsException("username required");
		}

	}

	@Override
	public Company retriveCompany(String userName) throws CompanyDetailsException {
		int i = 0;
		if (userName != null) {
			List<Company> companyDetails = companyRepository.findByUserName(userName);
			for (Company company : companyDetails) {
				if (company.getUserName().equalsIgnoreCase(userName) && i == 0) {
					++i;
					return company;
				} else {
					throw new CompanyDetailsException("username not present");
				}
			}
		} else {
			throw new CompanyDetailsException("username required");
		}

		return null;
	}

}