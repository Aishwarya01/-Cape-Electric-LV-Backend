package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.Testing;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface PeriodicTestingService {

	public void addTestInfo(Testing testing) throws PeriodicTestingException;

	public List<Testing> retrieveTestInfo(String userName, Integer siteId) throws PeriodicTestingException;

}
