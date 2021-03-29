package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.service.SiteService;
import com.capeelectric.util.ComparingCompanyDetailsUtil;

@RestController()
@RequestMapping("/api/v1")
public class SiteController {       //TODO ByUsing SiteController to store siteDetails in DB
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SiteService siteService;

	@Autowired
	private CompanyRepository companyRepository; // TODO delete this line
	@Autowired
	private DepartmentRepository departmentRepository; // TODO delete this line

	@PostMapping("/site")
	public ResponseEntity<String> insertSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called SiteController_Class insertSite_function SiteClientName : {},SiteDepartment : {}, Site : {}", site.getClientName(),
				site.getDepartmentName(), site.getSite());
		
		Boolean clientNameDeptCompanrison_Site = ComparingCompanyDetailsUtil
				.clientNameDeptCompanrison_Site(site.getClientName(),
						site.getDepartmentName(), companyRepository, departmentRepository);
		
		logger.info("called insertSite SiteClientNameSiteDepartmentNameComparingCompanyDepartmentSite : {}",
				clientNameDeptCompanrison_Site);
		
		siteService.insertSite(site, clientNameDeptCompanrison_Site);
		return new ResponseEntity<String>(site.getClientName() + " " + site.getDepartmentName() + "  " + site.getSite(),
				HttpStatus.OK);

	}

}
