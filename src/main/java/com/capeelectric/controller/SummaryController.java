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

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.service.SummaryService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController()
@RequestMapping("/api/v1")
public class SummaryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private SummaryService summaryService;

	@PostMapping("/addSummary")
	public ResponseEntity<String> addSummary(@RequestBody Summary summary) throws SummaryException {
		logger.info("started addSummary function userName: {},siteId : {}", summary.getUserName(),summary.getSiteId());
		summaryService.addSummary(summary);
		logger.info("ended addSummary function");
		return new ResponseEntity<String>("successfully added Summary", HttpStatus.OK);

	}
	
	@GetMapping("/retrieveSummary/{userName}/{siteId}")
	public ResponseEntity<List<Summary>> retrieveSummary(@PathVariable String userName,@PathVariable Integer siteId) throws SummaryException {
		logger.info("called retrieveSummary function userName: {},siteId : {}", userName,siteId);
		return new ResponseEntity<List<Summary>>(summaryService.retrieveSummary(userName,siteId), HttpStatus.OK);
	}
	
	@PutMapping("/updateSummary")
	public ResponseEntity<String> updateSummary(@RequestBody Summary summary)
			throws SummaryException {
		logger.info("called updateSummary function UserName : {},SiteId : {},SummaryId : {}", summary.getUserName(),
				summary.getSiteId(), summary.getSummaryId());
		summaryService.updateSummary(summary);
		return new ResponseEntity<String>("Summary successfully Updated", HttpStatus.CREATED);
	}

}
