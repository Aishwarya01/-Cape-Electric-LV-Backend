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

import com.capeelectric.exception.ClientDetailsException;
import com.capeelectric.model.ClientDetails;
import com.capeelectric.service.ClientDetailsService;

@RestController
@RequestMapping("/api/v2")
public class ClientDetailsController {
	private static final Logger logger = LoggerFactory.getLogger(ClientDetailsController.class);
	@Autowired
	private ClientDetailsService clientDetailsService;

	@PostMapping("/emc/saveClientDetails")
	public ResponseEntity<ClientDetails> saveClientDetails(@RequestBody ClientDetails clientDetails)
			throws ClientDetailsException {
		logger.debug("started saveClientDetails function userName: {}", clientDetails.getUserName());
		return new ResponseEntity<ClientDetails>(clientDetailsService.saveClientDetails(clientDetails),
				HttpStatus.CREATED);

	}

	@GetMapping("/emc/retrieveClientDetails/{userName}/{emcId}")
	public ResponseEntity<List<ClientDetails>> retrieveClientDetails(@PathVariable String userName,
			@PathVariable Integer emcId) throws ClientDetailsException {
		logger.info("called retrieveClientDetails function UserName: {}, emcId : {}", userName, emcId);
		return new ResponseEntity<List<ClientDetails>>(clientDetailsService.retrieveClientDetails(userName, emcId),
				HttpStatus.OK);
	}
 
	@PutMapping("/emc/updateClientDetails")
	public ResponseEntity<String> updateClientDetails(@RequestBody ClientDetails clientDetails)
			throws ClientDetailsException {
		logger.info("called updateClientDetails function UserName : {},getEmcId : {}", clientDetails.getUserName());
		clientDetailsService.updateClientDetails(clientDetails);
		return new ResponseEntity<String>("ClientDetails  Updated Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/emc/updateEmcStatus")
	public ResponseEntity<String> updateClientDetailsStatus(@RequestBody ClientDetails clientDetails)
			throws ClientDetailsException {
		logger.info("called updateClientDetailsStatus function UserName : {},getEmcId : {}", clientDetails.getUserName());
		clientDetailsService.updateClientDetailsStatus(clientDetails);
		return new ResponseEntity<String>("ClientDetails  has been successfully deleted", HttpStatus.OK);
	}
	
}
