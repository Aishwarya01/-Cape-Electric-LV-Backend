package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class SiteController {        
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SiteService siteService;

	@Autowired
	private CompanyRepository companyRepository; // TODO delete this line
	@Autowired
	private DepartmentRepository departmentRepository; // TODO delete this line

	@PostMapping("/addsite")
	public ResponseEntity<String> addSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called SiteController_Class insertSite_function ClientName : {},Department : {}, Site : {}",
				site.getClientName(), site.getDepartmentName(), site.getSite());

		Boolean clientNameDeptCompanrison_Site = ComparingCompanyDetailsUtil.clientNameDeptCompanrison_Site(
				site.getClientName(), site.getDepartmentName(), companyRepository, departmentRepository);

		logger.info("called insertSite SiteClientNameSiteDepartmentNameComparingCompanyDepartmentSite : {}",
				clientNameDeptCompanrison_Site);

		siteService.insertSite(site, clientNameDeptCompanrison_Site);
		return new ResponseEntity<String>(site.getClientName() + " " + site.getDepartmentName() + "  " + site.getSite(),
				HttpStatus.OK);

	}

	@PutMapping("/updatesite")
	public ResponseEntity<String> updateSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called updateSite function clientName: {},Department : {}, Site : {}", site.getUserName(),
				site.getDepartmentName(), site.getSite());
		siteService.updateSite(site);
		return new ResponseEntity<String>("Record Updated", HttpStatus.OK);
	}

	@PostMapping("/deleteSite")
	public ResponseEntity<String> deleteSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called deleteSite function clientName: {},Department : {}, Site : {}", site.getUserName(),
				site.getDepartmentName(), site.getSite());
		siteService.deleteSite(site);
		return new ResponseEntity<String>("Record Updated", HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/retrivesite")
	public ResponseEntity<List> retriveSite(@RequestBody Site site) throws CompanyDetailsException {
		logger.info("called retriveSite function clientName: {},Department : {}, Site : {}", site.getUserName(),
				site.getDepartmentName(), site.getSite());
		return new ResponseEntity<List>(siteService.retriveSite(site), HttpStatus.OK);
	}

}
