package com.capeelectric.service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;

public interface SiteService {
	public Site insertSite(Site site, Boolean clientNameDeptCompanrison_Site)
			throws CompanyDetailsException;
}
