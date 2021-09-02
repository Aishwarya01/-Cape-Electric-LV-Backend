package com.capeelectric.service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SupplyCharacteristicsException;

public interface PrintSupplyService {
	public void printSupply(String userName, Integer siteId) throws SupplyCharacteristicsException;
}

