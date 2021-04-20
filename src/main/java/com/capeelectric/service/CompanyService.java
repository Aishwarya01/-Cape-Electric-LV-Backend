package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
 
public interface CompanyService {

	public void addcompany(Company clientname) throws CompanyDetailsException;

	public void updateCompany(Company userName) throws CompanyDetailsException;

	public void deleteCompany(String userName,String clientname) throws CompanyDetailsException;

	public List<Company> retriveCompany(String userName) throws CompanyDetailsException;

}
