package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.TestInfoException;
import com.capeelectric.model.Testing;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface TestInfoService {

	public void addTestInfo(Testing testing) throws TestInfoException;

	public Optional<Testing> retrieveSummary(String userName, Integer siteId) throws TestInfoException;

}
