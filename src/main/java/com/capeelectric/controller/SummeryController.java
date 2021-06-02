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

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.service.SummeryService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController()
@RequestMapping("/api/v1")
public class SummeryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SummeryService summeryService;

	@PostMapping("/addSummery")
	public ResponseEntity<String> addSummary(@RequestBody Summary summary) throws SummaryException {
		logger.info("started addSummary function userName: {},siteId : {}", summary.getUserName(),summary.getSiteId());
		summeryService.addSummary(summary);
		logger.info("ended addSummery function");
		return new ResponseEntity<String>("successfully added Summery", HttpStatus.OK);

	}
	
	@GetMapping("/retrieveSummery/{userName}/{siteId}")
	public ResponseEntity<Optional<Summary>> retrieveSummary(@PathVariable String userName,@PathVariable Integer siteId) throws SummaryException {
		logger.info("called retrieveSummary function userName: {},siteId : {}", userName,siteId);
		return new ResponseEntity<Optional<Summary>>(summeryService.retrieveSummary(userName,siteId), HttpStatus.OK);
	}

}
