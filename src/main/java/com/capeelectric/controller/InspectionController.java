package com.capeelectric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.service.InspectionService;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController()
@RequestMapping("/api/v1")
public class InspectionController {

	@Autowired
	private InspectionService inspectionService;

	@PostMapping("/addInspectionDetails")
	public ResponseEntity<String> addInspectionDetails(@RequestBody IpaoInspection ipaoInspection)
			throws InspectionException {

		inspectionService.addInspectionDetails(ipaoInspection);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("/retrieveInspectionDetails/{userName}/{siteId}")
	public ResponseEntity<List<IpaoInspection>> retrieveInspectionDetails(@PathVariable String userName,
			@PathVariable Integer siteId) throws InspectionException {

		return new ResponseEntity<List<IpaoInspection>>(inspectionService.retrieveInspectionDetails(userName, siteId),
				HttpStatus.OK);
	}
}
