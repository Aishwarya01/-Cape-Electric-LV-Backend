package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
 
public interface CompanyService {

	public Company addcompany(Company clientname) throws CompanyDetailsException;

	public void updateCompany(Company userName) throws CompanyDetailsException;

	public void deleteCompany(String userName) throws CompanyDetailsException;

	public List<Company> retriveCompany(String userName) throws CompanyDetailsException;

}
