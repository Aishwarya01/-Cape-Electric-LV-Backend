package com.capeelectric.service.impl;

import java.time.LocalDateTime;

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
					" specified siteclientName sitedepartmentName not matched in Company and Department ");
		}

		else {
			site.setCreatedDate(LocalDateTime.now());
			site.setUpdatedDate(LocalDateTime.now());
		}

		return siteRepository.save(site);
	}
}
