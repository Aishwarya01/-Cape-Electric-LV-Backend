package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author capeelectricsoftware
 *
 */

@RestController
@RequestMapping("/api/v2")
public class NotificationController {

	
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	@GetMapping("/retrieveComments/{username}")
	public ResponseEntity<String> retrieveCommentsForUser(@PathVariable String userName){
		logger.debug("Retrieve Comments Starts");
		return null;
	}
}
