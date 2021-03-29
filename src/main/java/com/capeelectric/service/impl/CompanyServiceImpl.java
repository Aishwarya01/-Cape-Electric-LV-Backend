package com.capeelectric.service.impl;

import java.time.LocalDateTime;

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

	@Override
	public Company insertCompany(Company company) throws CompanyDetailsException {

		if (company.getClientName() == null) {
			throw new CompanyDetailsException("ClientName Required");
		} else {
			company.setCreatedDate(LocalDateTime.now());
			company.setUpdatedDate(LocalDateTime.now());
		}

		return companyRepository.save(company);
	}

}
