package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.TestingReport;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface PeriodicTestingService {

	public void addTestingReport(TestingReport testing) throws PeriodicTestingException;

	public List<TestingReport> retrieveTestingReport(String userName, Integer siteId) throws PeriodicTestingException;

}
