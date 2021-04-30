package com.capeelectric.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CountryDetailsException;
import com.capeelectric.model.State;
import com.capeelectric.repository.CountryRepository;
import com.capeelectric.repository.StateRepository;
import com.capeelectric.service.CountryDetailsService;

@Service
public class CountryDetailServiceImpl implements CountryDetailsService {
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;

	public List<State> fetchStatesByCountryName(String name) throws CountryDetailsException {
		List<State> stateList = stateRepository.fetchStatesByCountryName(name);
		return stateList;
	}
}