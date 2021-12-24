package com.capeelectric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.InstalReportException;
import com.capeelectric.exception.ObservationException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.service.InspectionServicePDF;
import com.capeelectric.service.InstalReportPDFService;
import com.capeelectric.service.PrintFinalPDFService;
import com.capeelectric.service.PrintService;
import com.capeelectric.service.PrintSupplyService;
import com.capeelectric.service.PrintTestingService;
import com.capeelectric.service.impl.AWSEmailService;

@RestController
@RequestMapping("api/v2")

public class MailPDFController {
	@Autowired
	private PrintService printService;

	@Autowired
	private PrintTestingService printTestingService;

	@Autowired
	private PrintSupplyService printSupplyService;

	@Autowired
	private PrintFinalPDFService printFinalPDFService;

	@Autowired
	private InstalReportPDFService instalReportPDFService;

	@Autowired
	private InspectionServicePDF inspectionServicePDF;

	@Autowired
	private AWSEmailService awsEmailService;

	@GetMapping("/sendPDFinMail/{userName}/{siteId}")
	public ResponseEntity<String> sendFinalPDF(@PathVariable String userName, @PathVariable Integer siteId)
			throws InstalReportException, SupplyCharacteristicsException, InspectionException, PeriodicTestingException,
			SummaryException, Exception, ObservationException {
		instalReportPDFService.printBasicInfromation(userName, siteId);
		printSupplyService.printSupply(userName, siteId);
		inspectionServicePDF.printInspectionDetails(userName, siteId);
		printTestingService.printTesting(userName, siteId);
		printService.printSummary(userName, siteId);
		printFinalPDFService.printFinalPDF(userName, siteId);
		awsEmailService.sendEmailPDF(userName);
		return new ResponseEntity<String>("Final PDF file has been sent to your registered mail id.", HttpStatus.OK);
	}
}
