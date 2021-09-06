package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface SummaryService {

	public void addSummary(Summary summary) throws SummaryException;

	public List<Summary> retrieveSummary(String userName, Integer siteId) throws SummaryException;

	public void updateSummary(Summary summary) throws SummaryException;

	public Summary sendComments(String userName, Integer siteId, String comments) throws SummaryException;

	public String replyComments(String userName, Integer siteId, String comments) throws SummaryException;

}
