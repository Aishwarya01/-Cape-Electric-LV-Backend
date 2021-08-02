package com.capeelectric.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;
import com.capeelectric.model.Company;
import com.capeelectric.model.User;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.service.AdminControllService;
import com.capeelectric.service.impl.AdminControllerServiceImpl;

@RestController
@RequestMapping("/api/v2")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	
    @Autowired
	private AdminControllService adminControllService;

	@PostMapping("/registerAdmin")
	public ResponseEntity<Void> addAdmin(@RequestBody Admin admin)  throws UserException {
		logger.debug("Add Managemnet starts");
		Admin createdAdmin = adminControllService.saveAdmin(admin);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAdmin.getId())
				.toUri();
		
		logger.debug("Add Managemnet ends");
		return ResponseEntity.created(uri).build();
	}
	

	@PutMapping("/updateManagementDetails")
	public ResponseEntity<String> updateManagementDetails(@RequestBody Admin admin)
			throws UserException, IOException, MessagingException {
		logger.debug("Update management details starts");
		adminControllService.updateManagementDetails(admin);
		logger.debug("Update management details Ends");
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/retrieveManagementInformation/{email}")
	public Admin retrieveManagementInformation(@PathVariable String email) throws UserException {
		return adminControllService.retrieveManagementInformation(email);
	}

	@DeleteMapping("/deleteAdmin/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId) throws UserException {
		logger.info("called deleteAdmin function adminId: {}", adminId);
		adminControllService.deleteById(adminId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

//	@GetMapping("/fetchUserslist")
//	public ResponseEntity<List<User>> getAllUser() throws UserException {
//		logger.debug("Fetching the  User&Viewer Details Starts ");
//		List<User> list = adminControllService.getAllUser();
//		logger.debug("Fetching the  User&Viewer Details Ends ");
//		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
//	}
//
//	@PutMapping("/AccessUserslist")
//	public ResponseEntity<String> updateAccessUserslist(@RequestBody List<User> user) throws UserException {
//		logger.debug("Updating The Access of User&Viewer Details Starts ");
//		adminControllService.updateAccessUserslist(user);
//		logger.debug("Updating The Access of User&Viewer Details Ends ");
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}
}
