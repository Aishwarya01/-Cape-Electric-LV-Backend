package com.capeelectric.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.service.AdminControllService;

@RestController
@RequestMapping("/api/v2")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AdminControllService adminControllService;

	@GetMapping("/fetchUserslist")
	public ResponseEntity<List<User>> getAllUser() throws UserException {
		logger.debug("Fetching the  User&Viewer Details Starts ");
		List<User> list = adminControllService.getAllUser();
		logger.debug("Fetching the  User&Viewer Details Ends ");
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PutMapping("/AccessUserslist")
	public ResponseEntity<String> updateAccessUserslist(@RequestBody List<User> user) throws UserException {
		logger.debug("Updating The Access of User&Viewer Details Starts ");
		adminControllService.updateAccessUserslist(user);
		logger.debug("Updating The Access of User&Viewer Details Ends ");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
