package com.capeelectric.service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
 
public interface CompanyService {

	public Company insertCompany(Company clientname) throws CompanyDetailsException;


}
