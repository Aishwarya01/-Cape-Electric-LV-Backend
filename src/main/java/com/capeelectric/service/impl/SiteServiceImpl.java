package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.model.Department;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.repository.SitePersonsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SitePersonsRepository sitePersonsRepository;

	/*
	 * @param Site addSite method to c comparing department client_name, comparing
	 * department_name,checking site_name
	 */
	@Override
	public void addSite(Site site) throws CompanyDetailsException {
		int count = 0;
 
		if (site.getClientName() != null && site.getDepartmentName() != null) {
			Optional<Company> companyRepo = companyRepository.findByClientName(site.getClientName());
			if (companyRepo.isPresent() && companyRepo.get() != null
					&& companyRepo.get().getClientName().equalsIgnoreCase(site.getClientName())) {
				Department department = departmentRepository.findByClientNameAndDepartmentName(site.getClientName(),
						site.getDepartmentName());
				if (department != null && department.getClientName().equalsIgnoreCase(site.getClientName())
						&& department.getDepartmentName().equalsIgnoreCase(site.getDepartmentName())) {
					Site siteRepo = siteRepository.findByClientNameAndDepartmentNameAndSite(site.getClientName(),
							site.getDepartmentName(), site.getSite());

					if (siteRepo == null || !siteRepo.getSite().equalsIgnoreCase(site.getSite())) {
						site.setDepartment(department);
						site.setSiteCd(site.getSite().substring(0, 3).concat("_0") + (count + 1));
						site.setCreatedDate(LocalDateTime.now());
						site.setUpdatedDate(LocalDateTime.now());
						site.setCreatedBy(generateFullName(department.getUserName()));
						site.setUpdatedBy(generateFullName(department.getUserName()));
						boolean email = checkSitePersonEmail(site.getSitePersons());
						if (email) {
							siteRepository.save(site);
						}else {
							throw new CompanyDetailsException("PersonInchargEmail already present");
						}
					} else {
						throw new CompanyDetailsException(site.getSite() + ": site already present");
					}

				} else {
					throw new CompanyDetailsException(site.getDepartmentName() + " : department not present  "
							+ site.getClientName() + " company");
				}
			} else {
				throw new CompanyDetailsException("clientName  " + site.getClientName() + "  not present "
						+ site.getDepartmentName() + " department");
			}
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}

	/*
	 * @param Site 
	 * updateSite method to comparing department_ClientName,
	 * department_name comparing, then comparing site  
	 */
	@Override
	public void updateSite(Site site) throws CompanyDetailsException {
		int count = 0;
		if (site.getDepartmentName() != null && site.getClientName() != null && site.getUserName() != null
				&& site.getSiteId() != null) {
			Department department = departmentRepository.findByClientNameAndDepartmentName(site.getClientName(),
					site.getDepartmentName());
			if (department != null && department.getClientName().equalsIgnoreCase(site.getClientName())) {
				if (department != null && department.getDepartmentName().equalsIgnoreCase(site.getDepartmentName())) {

					Site siteRepo = siteRepository.findByClientNameAndDepartmentNameAndSite(site.getClientName(),
							site.getDepartmentName(), site.getSite());
					
		 			Set<SitePersons> sitePersons = deleteSitePersonDetails(site.getSitePersons());
					if(!sitePersons.isEmpty()) {
						site.getSitePersons().removeAll(sitePersons);
					}
					if (siteRepo != null && siteRepo.getSite().equalsIgnoreCase(site.getSite())
							&& siteRepo.getSiteId().equals(site.getSiteId())) {
						site.setSiteCd(site.getSite().substring(0, 3).concat("_0") + (count + 1));
						site.setUpdatedDate(LocalDateTime.now());
						site.setUpdatedBy(generateFullName(department.getUserName()));
						boolean email = checkSitePersonEmail(site.getSitePersons());
						if (email) {
							site.setDepartment(department);
							siteRepository.save(site);
						}else {
							throw new CompanyDetailsException("PersonInchargEmail already present");
						}
					} else {
						throw new CompanyDetailsException(site.getSite() + " site not present");
					}

				} else {
					throw new CompanyDetailsException(site.getDepartmentName() + "  department not present for "
							+ site.getClientName() + " company");
				}

			} else {
				throw new CompanyDetailsException("clientName  " + site.getClientName() + "  not present "
						+ site.getDepartmentName() + " department");
			}
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}

	/*
	 * @param siteId deleteSite method to comparing siteId in site_table and @param
	 * siteId is true then site_object will be delete
	 */
	@Override
	public void deleteSite(Integer siteId) throws CompanyDetailsException, EmptyResultDataAccessException {
		if (siteId != null && siteId != 0) {
			Optional<Site> site = siteRepository.findById(siteId);

			if (site.isPresent() && site != null && site.get().getSiteId().equals(siteId)) {

				siteRepository.deleteById(siteId);
			} else {
				throw new CompanyDetailsException(siteId + " : this siteId not present");
			}

		} else {
			throw new CompanyDetailsException("invalid input");
		}

	}

	/*
	 * @param clientName,departmentName
	 * retriveSite method to retrieving site from DB
	 */
	@Override
	public List<Site> retriveSite(String clientName, String departmentName) throws CompanyDetailsException {
		if (clientName != null && departmentName != null) {
			return siteRepository.findByClientNameAndDepartmentName(clientName, departmentName);
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}

	private String generateFullName(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent() && user.get() != null)
			return user.get().getFirstname() + " " + user.get().getLastname();
		return "";
	}

	/*
	 * @param sitePersons
	 * checkSitePersonEmail method to finding duplicate personInchargeMail entry
	 */
	private boolean checkSitePersonEmail(Set<SitePersons> sitePersons) throws CompanyDetailsException {
		boolean emailAvailable = true;
		for (SitePersons sitePersonsItr : sitePersons) {

			Optional<SitePersons> inchargeEmail = sitePersonsRepository
					.findByPersonInchargeEmail(sitePersonsItr.getPersonInchargeEmail());

			if (inchargeEmail.isPresent() && inchargeEmail != null) {
				if (inchargeEmail.get().getPersonInchargeEmail()
						.equalsIgnoreCase(sitePersonsItr.getPersonInchargeEmail())
						&& inchargeEmail.get().getPersonId().equals(sitePersonsItr.getPersonId())) {
				} else {
					emailAvailable = false; 
				}
			}
		}
		return emailAvailable;
	}
	

	/**
	 * 
	 * @param sitePersons
	 */
	private Set<SitePersons> deleteSitePersonDetails(Set<SitePersons> sitePersons) {
		Set<SitePersons> sitePersonSet = new HashSet<SitePersons>();
		for (SitePersons sitePersonsItr : sitePersons) {
			if(sitePersonsItr !=null && !sitePersonsItr.getInActive()) {
				sitePersonsRepository.deleteById(sitePersonsItr.getPersonId());
				sitePersonSet.add(sitePersonsItr);
			}
		}
		return sitePersonSet;
	}

}
