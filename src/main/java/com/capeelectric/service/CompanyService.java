package com.capeelectric.service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Company;
 
public interface CompanyService {

	public Company insertCompany(Company clientname) throws UserException;


}
