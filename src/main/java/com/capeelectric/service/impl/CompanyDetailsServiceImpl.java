package com.capeelectric.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyException;
import com.capeelectric.model.Company;
import com.capeelectric.repository.CompanyDetailsRepository;
import com.capeelectric.service.CompanyDetailsService;

@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	@Override
	public Company insertCompany(Company company) throws CompanyException {
		logger.debug("insertCompany name Starts");
		if (company.getClientName() != null) {
			company.setCreateDate(LocalDateTime.now());
			company.setUpdateDate(LocalDateTime.now());
		} else {
			throw new CompanyException("ClientName Required");
		}
		logger.debug("insertCompany name ends");

		return companyDetailsRepository.save(company);
	}

}
