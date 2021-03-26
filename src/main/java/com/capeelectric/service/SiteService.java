package com.capeelectric.service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Site;

public interface SiteService {
	public Site insertSite(Site site, Boolean siteClientNameSiteDepartmentNameComparingCompanyDepartmentSite)
			throws UserException;
}
