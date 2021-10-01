package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;

public interface PrintService {
	public void printSummary(String userName, Integer siteId) throws SummaryException;
}
