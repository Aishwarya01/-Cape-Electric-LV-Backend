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

import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.service.SupplyCharacteristicsService;

/**
 *
 * @author capeelectricsoftware
 *
 */
@RestController()
@RequestMapping("/api/v1")
public class SupplyCharacteristicsController {

	private static final Logger logger = LoggerFactory.getLogger(SupplyCharacteristicsController.class);
	
	@Autowired
	private SupplyCharacteristicsService supplyCharacteristicsService;

	@PostMapping("/addCharacteristics")
	public ResponseEntity<String> addCharacteristics(@RequestBody SupplyCharacteristics supplyCharacteristics)
			throws SupplyCharacteristicsException {
		logger.info("called addCharacteristics function UserName : {}", supplyCharacteristics.getUserName());
		supplyCharacteristicsService.addCharacteristics(supplyCharacteristics);
		return new ResponseEntity<String>(" SupplyCharacteristics and earthing properties sucessfully added",
				HttpStatus.OK);
	}

	@GetMapping("/retrieveCharacteristics/{userName}/{siteId}")
	public ResponseEntity<List<SupplyCharacteristics>> retrieveCharacteristics(@PathVariable String userName,
			@PathVariable Integer siteId) throws SupplyCharacteristicsException {
		logger.info("started retrieveCharacteristics function UserName : {}",userName);
		return new ResponseEntity<List<SupplyCharacteristics>>(
				supplyCharacteristicsService.retrieveCharacteristics(userName, siteId), HttpStatus.OK);
	}

}
