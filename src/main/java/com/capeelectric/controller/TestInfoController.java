package com.capeelectric.controller;

import java.util.Optional;

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

import com.capeelectric.exception.TestInfoException;
import com.capeelectric.model.Testing;
import com.capeelectric.service.TestInfoService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController()
@RequestMapping("/api/v1")
public class TestInfoController {

	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private TestInfoService testService;

	@PostMapping("/addTestInfo")
	public ResponseEntity<String> addTestInfo(@RequestBody Testing testing) throws TestInfoException {
		logger.info("started addTestInfo function userName: {},siteId : {}", testing.getUserName(),
				testing.getSiteId());

		testService.addTestInfo(testing);
		logger.info("ended addTestInfo function");

		return new ResponseEntity<String>("successfully added TestInfo", HttpStatus.OK);

	}

	@GetMapping("/retrieveTestInfo/{userName}/{siteId}")
	public ResponseEntity<Optional<Testing>> retrieveTestInfo(@PathVariable String userName,
			@PathVariable Integer siteId) throws TestInfoException {
		logger.info("Started retrieveTestInfo function userName: {},siteId : {}", userName, siteId);

		return new ResponseEntity<Optional<Testing>>(testService.retrieveSummary(userName, siteId), HttpStatus.OK);
	}

}
