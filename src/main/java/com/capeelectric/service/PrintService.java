package com.capeelectric.service;

import com.capeelectric.exception.SummaryException;

public interface PrintService {
	public void printSummary(String userName, Integer siteId) throws SummaryException;
}
