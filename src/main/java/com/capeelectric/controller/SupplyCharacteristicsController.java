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

import com.capeelectric.exception.DecimalConversionException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristicComment;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.service.SupplyCharacteristicsService;
import com.capeelectric.util.SendReplyComments;

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
	
	@Autowired
	private SendReplyComments sendReplyComments;

	@PostMapping("/addCharacteristics")
	public ResponseEntity<String> addCharacteristics(@RequestBody SupplyCharacteristics supplyCharacteristics)
			throws SupplyCharacteristicsException, DecimalConversionException {
		logger.info("called addCharacteristics function UserName : {}, SiteId : {}",
				supplyCharacteristics.getUserName(), supplyCharacteristics.getSiteId());
		supplyCharacteristicsService.addCharacteristics(supplyCharacteristics);
		return new ResponseEntity<String>("SupplyCharacteristics and Earthing Properties Sucessfully Saved",
				HttpStatus.CREATED);
	}

	@GetMapping("/retrieveCharacteristics/{userName}/{siteId}")
	public ResponseEntity<List<SupplyCharacteristics>> retrieveCharacteristics(@PathVariable String userName,
			@PathVariable Integer siteId) throws SupplyCharacteristicsException {
		logger.info("started retrieveCharacteristics function UserName : {}, SiteId : {}", userName, siteId);
		return new ResponseEntity<List<SupplyCharacteristics>>(
				supplyCharacteristicsService.retrieveCharacteristics(userName, siteId), HttpStatus.OK);
	}

	@PutMapping("/updateCharacteristics")
	public ResponseEntity<String> updateCharacteristics(@RequestBody SupplyCharacteristics supplyCharacteristics)
			throws SupplyCharacteristicsException, DecimalConversionException {
		logger.info("called updateCharacteristics function UserName : {},SiteId : {},SupplyCharacteristicsId : {}",
				supplyCharacteristics.getUserName(), supplyCharacteristics.getSiteId(),
				supplyCharacteristics.getSupplyCharacteristicsId());
		supplyCharacteristicsService.updateCharacteristics(supplyCharacteristics);
		return new ResponseEntity<String>("SupplyCharacteristics Data successfully Updated", HttpStatus.OK);
	}

	@PostMapping("/sendCharacteristicsComments/{userName}/{siteId}")
	public ResponseEntity<Void> sendComments(@PathVariable String userName, @PathVariable Integer siteId,
			@RequestBody SupplyCharacteristicComment supplyCharacteristicComment)
			throws SupplyCharacteristicsException, RegistrationException, Exception {
		logger.info("called sendcomments function UserName : {},SiteId : {}", userName, siteId);
		supplyCharacteristicsService.sendComments(userName, siteId, supplyCharacteristicComment);
		sendReplyComments.sendComments(userName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/replyCharacteristicsComments/{inspectorUserName}/{siteId}")
	public ResponseEntity<Void> replyComments(@PathVariable String inspectorUserName, @PathVariable Integer siteId,
			@RequestBody SupplyCharacteristicComment supplyCharacteristicComment)
			throws SupplyCharacteristicsException, RegistrationException, Exception {
		logger.info("called replyComments function inspectorUserName : {},SiteId : {}", inspectorUserName, siteId);
		String viewerUserName = supplyCharacteristicsService.replyComments(inspectorUserName, siteId,
				supplyCharacteristicComment);
		if (viewerUserName != null) {
			sendReplyComments.replyComments(inspectorUserName, viewerUserName);
		} else {
			throw new SupplyCharacteristicsException("No viewer userName avilable");
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/approveCharacteristicsComments/{userName}/{siteId}")
	public ResponseEntity<Void> approveComments(@PathVariable String userName, @PathVariable Integer siteId,
			@RequestBody SupplyCharacteristicComment supplyCharacteristicComment)
			throws SupplyCharacteristicsException, RegistrationException, Exception {
		logger.info("called approveComments function UserName : {},SiteId : {}", userName, siteId);
		supplyCharacteristicsService.approveComments(userName, siteId, supplyCharacteristicComment);
		sendReplyComments.approveComments(userName, supplyCharacteristicComment.getApproveOrReject());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
