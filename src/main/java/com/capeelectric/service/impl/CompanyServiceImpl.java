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

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void addcompany(Company company) throws CompanyDetailsException {

		int count = 0;
		if (company.getClientName() != null) {
			Company client = companyRepository.findByClientName(company.getClientName());

			if (client != null && client.getClientName().equalsIgnoreCase(company.getClientName())) {
				throw new CompanyDetailsException("ClientName already present");
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

	@Override
	public void updateCompany(Company company) throws CompanyDetailsException {
		Boolean flag = false;

		if (company.getUserName() != null && company.getClientName() != null && company.getCompanyId() != null) {

			List<Company> findByUserName = companyRepository.findByUserName(company.getUserName());
			for (Company companys : findByUserName) {
				if (companys.getUserName().equalsIgnoreCase(company.getUserName())
						&& companys.getCompanyId().equals(company.getCompanyId())) {
					company.setUpdatedBy(generateFullName(company.getUserName()));
					company.setUpdatedDate(LocalDateTime.now());
					companyRepository.save(company);
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new CompanyDetailsException("User not present");
			}

		} else {
			throw new CompanyDetailsException("Client and username required");
		}

	}

	@Override
	public void deleteCompany(String userName, String clientName) throws CompanyDetailsException {

		if (userName != null && clientName != null) {
			Company clientRepo = companyRepository.findByClientName(clientName);

			if (clientRepo != null && clientRepo.getClientName().equalsIgnoreCase(clientName)) {
				companyRepository.delete(clientRepo);
			} else {
				throw new CompanyDetailsException("client not present");
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