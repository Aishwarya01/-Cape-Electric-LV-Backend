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

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.TestingReport;
import com.capeelectric.model.TestingReportComment;
import com.capeelectric.service.PeriodicTestingService;
import com.capeelectric.util.SendReplyComments;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController()
@RequestMapping("/api/v1")
public class PeriodicTestingController {

	private static final Logger logger = LoggerFactory.getLogger(PeriodicTestingController.class);

	@Autowired
	private PeriodicTestingService periodicTestingService;
	
	@Autowired
	private SendReplyComments sendReplyComments;

	@PostMapping("/savePeriodicTesting")
	public ResponseEntity<String> savePeriodicTesting(@RequestBody TestingReport testingReport)
			throws PeriodicTestingException {
		logger.info("started savePeriodicTesting function userName: {},siteId : {}", testingReport.getUserName(),
				testingReport.getSiteId());

		periodicTestingService.addTestingReport(testingReport);
		logger.info("ended savePeriodicTesting function");

		return new ResponseEntity<String>("Testing Report Successfully Saved", HttpStatus.OK);

	}

	@GetMapping("/retrievePeriodicTesting/{userName}/{siteId}")
	public ResponseEntity<List<TestingReport>> retrievePeriodicTesting(@PathVariable String userName,
			@PathVariable Integer siteId) throws PeriodicTestingException {
		logger.info("Started retrievePeriodicTesting function userName: {},siteId : {}", userName, siteId);
		List<TestingReport> retrieveTestingReport = periodicTestingService.retrieveTestingReport(userName, siteId);
		logger.info("ended retrievePeriodicTesting function");

		return new ResponseEntity<List<TestingReport>>(retrieveTestingReport, HttpStatus.OK);
	}


	@PutMapping("/updatePeriodicTesting")
	public ResponseEntity<String> updatePeriodicTesting(@RequestBody TestingReport testingReport)
			throws PeriodicTestingException {
		logger.info("called updatePeriodicTesting function UserName : {},SiteId : {},TestingReportId : {}",
				testingReport.getUserName(), testingReport.getSiteId(),
				testingReport.getTestingReportId());
		periodicTestingService.updatePeriodicTesting(testingReport);
		return new ResponseEntity<String>("PeriodicTesting successfully Updated", HttpStatus.OK);
	}

	@PostMapping("/sendTestingComments/{userName}/{siteId}")
	public ResponseEntity<Void> sendComments(@PathVariable String userName,
			@PathVariable Integer siteId,@RequestBody TestingReportComment testingReportComment)
			throws PeriodicTestingException, RegistrationException, Exception {
		logger.info("called sendComments function UserName : {},SiteId : {}", userName, siteId);
		periodicTestingService.sendComments(userName, siteId, testingReportComment);
		sendReplyComments.sendComments(userName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/replyTestingComments/{inspectorUserName}/{siteId}")
	public ResponseEntity<Void> replyComments(@PathVariable String inspectorUserName, @PathVariable Integer siteId,
			@RequestBody TestingReportComment testingReportComment)
			throws PeriodicTestingException, RegistrationException, Exception {
		logger.info("called replyComments function inspectorUserName : {},SiteId : {}", inspectorUserName, siteId);
		String viewerUserName = periodicTestingService.replyComments(inspectorUserName, siteId, testingReportComment);
		if (viewerUserName != null) {
			sendReplyComments.replyComments(inspectorUserName, viewerUserName);
		} else {
			throw new PeriodicTestingException("No viewer userName avilable");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/approveTestingComments/{userName}/{siteId}")
	public ResponseEntity<Void> approveComments(@PathVariable String userName, @PathVariable Integer siteId,
			@RequestBody TestingReportComment testingReportComment)
			throws PeriodicTestingException, RegistrationException, Exception {
		logger.info("called approveComments function UserName : {},SiteId : {}", userName, siteId);
		periodicTestingService.approveComments(userName, siteId, testingReportComment);
		sendReplyComments.approveComments(userName, testingReportComment.getApproveOrReject());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
