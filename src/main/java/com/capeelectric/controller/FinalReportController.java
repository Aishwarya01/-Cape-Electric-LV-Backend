
package com.capeelectric.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.Site;
import com.capeelectric.service.impl.FinalReportServiceImpl;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v1")
public class FinalReportController {

	private static final Logger logger = LoggerFactory.getLogger(FinalReportController.class);

	@Autowired
	FinalReportServiceImpl finalReportServiceImpl;

	@GetMapping("/retrieveListOfSite/{userName}/{departmentName}")
	public ResponseEntity<List<Site>> retrieveListOfSite(@PathVariable String userName,
			@PathVariable String departmentName) throws FinalReportException {
		logger.info("FinalReportAPI_started retrieveSiteDetails function userName: {},departmentName : {}", userName,
				departmentName);
		
		return new ResponseEntity<List<Site>>(finalReportServiceImpl.retrieveListOfSite(userName, departmentName),
				HttpStatus.OK);

	}

	@GetMapping("/retrieveFinalReport/{userName}/{siteId}")
	public ResponseEntity<Optional<FinalReport>> retrieveFinalReport(@PathVariable String userName,
			@PathVariable Integer siteId) throws FinalReportException {
		logger.info("FinalReportAPI_started retrieveFinalReport function userName: {},siteId : {}", userName, siteId);
		
		return new ResponseEntity<Optional<FinalReport>>(finalReportServiceImpl.retrieveFinalReport(userName, siteId),
				HttpStatus.OK);

	}

}