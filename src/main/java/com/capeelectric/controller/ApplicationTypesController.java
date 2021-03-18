package com.capeelectric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.model.ApplicationTypes;
import com.capeelectric.service.ApplicationTypesService;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v1")
public class ApplicationTypesController {
	
	@Autowired
	private ApplicationTypesService applicationTypesService;

	@GetMapping("/retrieveApplicationTypes")
	public List<ApplicationTypes> retrieveApplicationTypes(){
		
		return applicationTypesService.retrieveTypes();
	}
}
