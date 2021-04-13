package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;

public interface SiteService {
	public Site insertSite(Site site, Boolean clientNameDeptCompanrison_Site)
			throws CompanyDetailsException;

	public void updateSite(Site site) throws CompanyDetailsException;

	public void deleteSite(Site site) throws CompanyDetailsException;

	public List<Site> retriveSite(Site site) throws CompanyDetailsException;
}
