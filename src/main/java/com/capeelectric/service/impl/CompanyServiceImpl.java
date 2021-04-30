package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.CompanyService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;

	/*
	 * @param company
	 * addCompany method to retrieve the company based on userName,clientName
	 * if client not there company will be saved 
	 * if client available return exception
	 * 
	 */
	@Override
	public void addCompany(Company company) throws CompanyDetailsException {
		int count = 0;
				
		if (company.getClientName() != null) {
			 Optional<Company> clientRepo = companyRepository.findByUserNameAndClientName(company.getUserName(),company.getClientName());

			if (clientRepo.isPresent() && clientRepo.get() != null && clientRepo.get().getClientName().equalsIgnoreCase(company.getClientName())) {
				throw new CompanyDetailsException(company.getClientName()+" : this ClientName already present");
			} else { 
				company.setCompanyCd(company.getClientName().substring(0, 3).concat("_0")+(count+1));																	 
				company.setCreatedDate(LocalDateTime.now());
				company.setUpdatedDate(LocalDateTime.now());
				company.setCreatedBy(generateFullName(company.getUserName()));
				company.setUpdatedBy(generateFullName(company.getUserName()));
				companyRepository.save(company);
			}

		} else {
			throw new CompanyDetailsException("invalid input");
		}

	}
	
	/*
	 * @param company
	 * updateCompany method to retrieve the company based on companyID
 	 * if client available company will be updated 
	 * 
	 */
	@Override
	public void updateCompany(Company company) throws CompanyDetailsException {
		int count = 0;
		Boolean flag = false;

		if (company.getUserName() != null && company.getClientName() != null) {

			List<Company> findByUserName = companyRepository.findByUserName(company.getUserName());
			if (findByUserName != null && !findByUserName.isEmpty()) {
				for (Company companys : findByUserName) {
					if (companys != null && companys.getUserName().equalsIgnoreCase(company.getUserName())
							&& companys.getCompanyId().equals(company.getCompanyId())) {
						company.setCompanyCd(company.getClientName().substring(0, 3).concat("_0") + (count + 1));
						company.setUpdatedBy(generateFullName(company.getUserName()));
						company.setUpdatedDate(LocalDateTime.now());
						companyRepository.save(company);
						flag = true;
						break;
					}
				}
				if (!flag) {
					throw new CompanyDetailsException(
							company.getClientName() + " client not present user :" + company.getUserName());
				}
			} else {
				throw new CompanyDetailsException(company.getUserName() + "user not having company");
			}
		} else {
			throw new CompanyDetailsException("invalid input");
		}

	}

	/*
	 * @param userName,clientName
	 * deleteCompany method to retrieve the company based on userName,clientName
	 * if client not there return exception
	 * if client available company will be delete
	 * 
	 */
	@Override
	public void deleteCompany(String userName, String clientName) throws CompanyDetailsException {

		if (userName != null && clientName != null) {
 			 Optional<Company> clientRepo = companyRepository.findByUserNameAndClientName(userName,clientName);

			if (clientRepo.isPresent() && clientRepo.get() != null && clientRepo.get().getClientName().equalsIgnoreCase(clientName)) {
				companyRepository.deleteById(clientRepo.get().getCompanyId());
			} else {
				throw new CompanyDetailsException(clientName +" :client not present");
			}

		} else {
			throw new CompanyDetailsException("invalid inputs");
		}

	}
	
	/*
	 * @param userName
	 * retrieveCompany method to fetch data 
	 * 
	 */
	@Override
	public List<Company> retrieveCompany(String userName) throws CompanyDetailsException {

		if (userName != null) {
			return companyRepository.findByUserName(userName);

		} else {
			throw new CompanyDetailsException("username required");
		}
	}
	
	/**
	 * 
	 * @param userName
	 * @return
	 */
	private String generateFullName(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if(user.isPresent() && user.get()!= null) 
			return user.get().getFirstname() + " "+ user.get().getLastname();
		return "";
	}

}