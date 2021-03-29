package com.capeelectric.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capeelectric.exception.CompanyDetailsException;

import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;

public class ComparingCompanyDetailsUtil {
	private static final Logger logger = LoggerFactory.getLogger(ComparingCompanyDetailsUtil.class);

	public static Boolean  clientNameCompanrison_Department(String clientName, CompanyRepository companyRepository)
			throws CompanyDetailsException {
		List<String> findByClientName = companyRepository.findByClientName(clientName);

		if (findByClientName.contains(clientName)) {
			logger.info(
					"ComparingCompanyDetailsUtil class :  DepartmentClientNameComparingFromCompany_Department Company CompanyClientname: {}",
					findByClientName.contains(clientName));
			return true;
		} else {
			throw new CompanyDetailsException("specifed companyClientname not avilble");

		}

	}

	public static Boolean clientNameDeptCompanrison_Site(String siteClientName,
			String siteDepartmentName, CompanyRepository companyRepository, DepartmentRepository departmentRepository)
			throws CompanyDetailsException {
		List<String> companyClientnames = companyRepository.findByClientName(siteClientName);

		List<String> departmentClientNames = departmentRepository.findByClientName(siteClientName);

		List<String> departmentDepartmentNames = departmentRepository.findByDepartmentName(siteDepartmentName);

		if (companyClientnames.contains(siteClientName)) {
			logger.info(
					"ComparingCompanyDetailsUtil class : SiteClientNameComparingFromCompany_Site Company companyClientnames: {}",
					companyClientnames.contains(siteClientName));
			if (departmentClientNames.contains(siteClientName)) {
				logger.info(
						"ComparingCompanyDetailsUtil class : SiteClientNameComparingFromDepartment_Site Department departmentClientNames: {}",
						departmentClientNames.contains(siteClientName));
				if (departmentDepartmentNames.contains(siteDepartmentName)) {
					logger.info(
							"ComparingCompanyDetailsUtil class : SiteDepartmentNameComparingFromDepartment_Site Department departmentDepartmentName: {}",
							departmentDepartmentNames.contains(siteDepartmentName));
					return true;
				} else {
					throw new CompanyDetailsException("specifed departmentDepartmentName not avilble");
				}
			} else {
				throw new CompanyDetailsException("specifed departmentClientName not avilble");
			}
		} else {
			throw new CompanyDetailsException("specifed companyClientname not avilble");
		}
	}

}
