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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.service.InstalReportService;

/**
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v1")
public class InstallReportController {

	private static final Logger logger = LoggerFactory.getLogger(InstallReportController.class);

	@Autowired
	private InstalReportService instalReportService;

	@PostMapping("/addInstalReport")
	public ResponseEntity<String> addInstallationReport(@RequestBody ReportDetails reportDetails)
			throws InstalReportException {
		logger.info("called addInstallationReport function UserName : {},siteeId", reportDetails.getUserName(),
				reportDetails.getSiteId());
		instalReportService.addInstallationReport(reportDetails);
		return new ResponseEntity<String>("Report successfully saved", HttpStatus.CREATED);
	}

	@GetMapping("/retrieveInstalReport/{userName}/{siteId}")
	public ResponseEntity<List<ReportDetails>> retrieveInstallationReport(@PathVariable String userName,
			@PathVariable Integer siteId)
			throws InstalReportException {
		logger.info("called retrieveInstallationReport function userName: {},siteId : {}", userName, siteId);
		return new ResponseEntity<List<ReportDetails>>(instalReportService.retrieveInstallationReport(userName,siteId),
				HttpStatus.OK);
	}
}
