package com.capeelectric.service;

import com.capeelectric.exception.CompanyException;
import com.capeelectric.model.Company;

public interface CompanyDetailsService {

	public Company insertCompany(Company clientname) throws CompanyException;

}
