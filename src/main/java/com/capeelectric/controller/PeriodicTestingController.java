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

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.TestingReport;
import com.capeelectric.service.PeriodicTestingService;

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
	private PeriodicTestingService testService;

	@PostMapping("/savePeriodicTesting")
	public ResponseEntity<String> savePeriodicTesting(@RequestBody TestingReport testingReport)
			throws PeriodicTestingException {
		logger.info("started savePeriodicTesting function userName: {},siteId : {}", testingReport.getUserName(),
				testingReport.getSiteId());

		testService.addTestingReport(testingReport);
		logger.info("ended savePeriodicTesting function");

		return new ResponseEntity<String>("successfully added TestingReport", HttpStatus.OK);

	}

	@GetMapping("/retrievePeriodicTesting/{userName}/{siteId}")
	public ResponseEntity<List<TestingReport>> retrievePeriodicTesting(@PathVariable String userName,
			@PathVariable Integer siteId) throws PeriodicTestingException {
		logger.info("Started retrievePeriodicTesting function userName: {},siteId : {}", userName, siteId);
		List<TestingReport> retrieveTestingReport = testService.retrieveTestingReport(userName, siteId);
		logger.info("ended retrievePeriodicTesting function");

		return new ResponseEntity<List<TestingReport>>(retrieveTestingReport, HttpStatus.OK);
	}

}
