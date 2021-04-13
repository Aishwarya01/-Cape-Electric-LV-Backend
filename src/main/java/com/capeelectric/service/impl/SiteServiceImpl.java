package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);

	@Autowired
	private SiteRepository siteRepository;
	
	Boolean flag=false;

	@Override
	public Site insertSite(Site site, Boolean clientNameDeptCompanrison_Site)
			throws CompanyDetailsException {
		logger.debug("called SiteServiceImpl_class insertSite_function");
		if (site.getClientName() == null) {
			throw new CompanyDetailsException("ClientName Required");
		}
		else if (site.getDepartmentName() == null) {
			throw new CompanyDetailsException("DepartmentName Required");
		}
		else if (clientNameDeptCompanrison_Site == false) {
			throw new CompanyDetailsException(
					" specified clientName departmentName not matched");
		}

		else {
			site.setCreatedDate(LocalDateTime.now());
			site.setUpdatedDate(LocalDateTime.now());
		}

		return siteRepository.save(site);
	}

	@Override
	public void updateSite(Site site) throws CompanyDetailsException {
		if (site.getDepartmentName() != null && site.getClientName() != null
				&& site.getUserName() != null) {
 			List<Site> username = siteRepository.findByUserNameAndClientNameAndDepartmentName(site.getUserName(),site.getClientName(),site.getDepartmentName());
 			logger.info("called updateDepartment function clientName: {}", username);
			for (Site siteRepo : username) {
				if (siteRepo.getSiteId().equals(site.getSiteId())) {
					if (siteRepo.getUserName().equalsIgnoreCase(site.getUserName())) {
						if (siteRepo.getClientName().equalsIgnoreCase(site.getClientName())) {
							siteRepository.save(site);
							flag = true;
							break;
						} 
					} 
				}
				if (!flag) {
					throw new CompanyDetailsException("data not present");
				}
			}
	
		} else {
			throw new CompanyDetailsException("invalid required");
		}		
	}

	@Override
	public void deleteSite(Site site) throws CompanyDetailsException {
		if (site.getDepartmentName() != null && site.getClientName() != null
				&& site.getUserName() != null) {
 			List<Site> username = siteRepository.findByUserNameAndClientNameAndDepartmentName(site.getUserName(),site.getClientName(),site.getDepartmentName());
 			logger.info("called updateDepartment function clientName: {}", username);
			for (Site siteRepo : username) {
				if (siteRepo.getSiteId().equals(site.getSiteId())) {
					if (siteRepo.getUserName().equalsIgnoreCase(site.getUserName())) {
						if (siteRepo.getClientName().equalsIgnoreCase(site.getClientName())) {
							siteRepository.delete(site);
							flag = true;
							break;
						} 
					} 
				}
				if (!flag) {
					throw new CompanyDetailsException("data not present");
				}
			}
	
		} else {
			throw new CompanyDetailsException("invalid required");
		}
		
	}

	@Override
	public List<Site> retriveSite(Site site) throws CompanyDetailsException {
		if (site.getUserName() != null && site.getClientName() != null) {
			return siteRepository.findByUserNameAndClientNameAndDepartmentName(site.getUserName(), site.getClientName(),
					site.getDepartmentName());
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}
}
