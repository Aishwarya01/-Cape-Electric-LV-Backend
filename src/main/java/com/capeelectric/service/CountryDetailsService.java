package com.capeelectric.service;


import java.util.List;

import com.capeelectric.exception.CountryDetailsException;
import com.capeelectric.model.State;

public interface CountryDetailsService {
	public List<State> fetchStatesByCountryName(String name) throws CountryDetailsException;
}