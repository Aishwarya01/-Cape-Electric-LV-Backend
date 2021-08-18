package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.service.InspectionService;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v1")
public class InspectionController {

	private static final Logger logger = LoggerFactory.getLogger(InspectionController.class);

	@Autowired
	private InspectionService inspectionService;

	@PostMapping("/addInspectionDetails")
	public ResponseEntity<String> addInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException {
		logger.info("called addInspectionDetails function UserName : {},SiteId : {}", periodicInspection.getUserName(),
				periodicInspection.getSiteId());
		inspectionService.addInspectionDetails(periodicInspection);
		return new ResponseEntity<String>("Inspection Details Are Successfully Saved",HttpStatus.CREATED);
	}
	
	@GetMapping("/retrieveInspectionDetails/{userName}/{siteId}")
	public ResponseEntity<List<PeriodicInspection>> retrieveInspectionDetails(@PathVariable String userName,
			@PathVariable Integer siteId) throws InspectionException {
		logger.info("called addInspectionDetails function UserName : {},SiteId : {}", userName, siteId);
		return new ResponseEntity<List<PeriodicInspection>>(inspectionService.retrieveInspectionDetails(userName, siteId),
				HttpStatus.OK);
	}
	
	@PutMapping("/updateInspectionDetails")
	public ResponseEntity<String> updateInspectionDetails(@RequestBody PeriodicInspection periodicInspection)
			throws InspectionException {
		logger.info("called updateInspectionDetails function UserName : {},SiteId : {},PeriodicInspectionId : {}",
				periodicInspection.getUserName(), periodicInspection.getSiteId(),
				periodicInspection.getPeriodicInspectionId());
		inspectionService.updateInspectionDetails(periodicInspection);
		return new ResponseEntity<String>("Report successfully Updated", HttpStatus.OK);
	}
}
