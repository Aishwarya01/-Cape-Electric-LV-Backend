package com.capeelectric.service;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.exception.SupplyCharacteristicsException;

public interface PrintTestingService {

	public void printTesting(String userName, Integer siteId) throws PeriodicTestingException ;
}
