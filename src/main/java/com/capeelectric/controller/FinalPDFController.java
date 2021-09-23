package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.InstalReportException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.service.InspectionServicePDF;
import com.capeelectric.service.InstalReportPDFService;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintService;
import com.capeelectric.service.PrintSupplyService;
import com.capeelectric.service.PrintTestingService;

@RestController
@RequestMapping("/api/v1")
public class FinalPDFController {
	private static final Logger logger = LoggerFactory.getLogger(FinalReportController.class);
	
	@Autowired
	private PrintService printService ;
	
	@Autowired
	private PrintTestingService printTestingService ;
	
	@Autowired
	private PrintSupplyService printSupplyService ;
	
	@Autowired
	private PrintFinalPDFService printFinalPDFService;
	
	@Autowired
	private InstalReportPDFService instalReportPDFService;
	
	@Autowired
	private InspectionServicePDF inspectionServicePDF;
	
	@GetMapping("/printFinalPDF/{userName}/{siteId}")
	
	public ResponseEntity printFinalPDF(@PathVariable String userName,
			@PathVariable Integer siteId, Object writer) throws Exception, SummaryException, PeriodicTestingException, SupplyCharacteristicsException, InstalReportException, InspectionException {
		logger.info("called printFinalPDF function UserName : {},SiteId : {}", userName, siteId);
		
		instalReportPDFService.printBasicInfromation(userName,siteId);
		printSupplyService.printSupply(userName,siteId);
		inspectionServicePDF.retrieveInspectionDetails(userName,siteId);
		printTestingService.printTesting(userName, siteId);
		printService.printSummary(userName,siteId);
		printFinalPDFService.printFinalPDF(userName, siteId);
	     return new ResponseEntity(HttpStatus.OK);
		}
	
}
