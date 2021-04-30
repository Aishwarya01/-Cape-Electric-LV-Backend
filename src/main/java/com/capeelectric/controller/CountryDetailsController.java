package com.capeelectric.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.CountryDetailsException;
import com.capeelectric.model.State;
import com.capeelectric.service.CountryDetailsService;

@RestController
@RequestMapping("/api/v1")
public class CountryDetailsController {
	
	@Autowired(required=true)
	private CountryDetailsService countryDetailsService;

	@GetMapping("/fetchStatesByCountryName/{countryName}")
	public List<State> fetchStatesByCountryName(@PathVariable String countryName) throws CountryDetailsException {
		return countryDetailsService.fetchStatesByCountryName(countryName);
	}
}

