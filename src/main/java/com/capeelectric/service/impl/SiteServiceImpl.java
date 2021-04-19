package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	public void insertSite(Site site, Boolean clientNameDeptCompanrison_Site) throws CompanyDetailsException {
		logger.debug("called SiteServiceImpl_class insertSite_function");
		if (site.getClientName() != null && site.getDepartmentName() != null) {
			if (clientNameDeptCompanrison_Site != false) {
				List<Site> siteRepo = siteRepository.findByUserNameAndClientNameAndDepartmentName(site.getUserName(),
						site.getClientName(), site.getDepartmentName());
				for (Site sites : siteRepo) {
					if (sites.getSite().equalsIgnoreCase(site.getSite())) {
						flag = true;
						throw new CompanyDetailsException("site already present");
					}
				}
			}
			if (!false) {
				site.setCreatedDate(LocalDateTime.now());
				site.setUpdatedDate(LocalDateTime.now());
				siteRepository.save(site);

			}
		} else {
			throw new CompanyDetailsException("invalid inputs");
		}

	}

	@Override
	public void updateSite(Site site, Boolean clientNameDeptCompanrison_Site) throws CompanyDetailsException {
		if (site.getDepartmentName() != null && site.getClientName() != null && site.getUserName() != null
				&& site.getSiteId() != null) {
			Optional<Site> siteRepo = siteRepository.findById(site.getSiteId());
			if (clientNameDeptCompanrison_Site) {
				if (siteRepo.get().getSiteId().equals(site.getSiteId())) {
					site.setUpdatedDate(LocalDateTime.now());
					siteRepository.save(site);

				} else {
					throw new CompanyDetailsException("site not present");
				}
			} else {
				throw new CompanyDetailsException(" specified clientName departmentName not matched");
			}

		} else {
			throw new CompanyDetailsException("invalid inputs");
		}
	}

	@Override
	public void deleteSite(Integer siteId) throws CompanyDetailsException {
		if (siteId != null && siteId != 0) {
			Optional<Site> site = siteRepository.findById(siteId);

			if (site.get().getSiteId().equals(siteId)) {
				siteRepository.deleteById(siteId);
			} else {
				throw new CompanyDetailsException("invaild site");
			}

		} else {
			throw new CompanyDetailsException("invalid input");
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
