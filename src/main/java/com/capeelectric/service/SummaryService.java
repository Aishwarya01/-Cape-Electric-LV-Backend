package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface SummaryService {

	public void addSummary(Summary summary) throws SummaryException;

	public Optional<Summary> retrieveSummary(String userName, Integer siteId) throws SummaryException;

}
