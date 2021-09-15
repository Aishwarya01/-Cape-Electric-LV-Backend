package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryComment;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface SummaryService {

	public void addSummary(Summary summary) throws SummaryException;

	public List<Summary> retrieveSummary(String userName, Integer siteId) throws SummaryException;

	public void updateSummary(Summary summary) throws SummaryException;

	public void sendComments(String userName, Integer siteId, SummaryComment summaryComment) throws SummaryException;

	public String replyComments(String userName, Integer siteId, SummaryComment summaryComment) throws SummaryException;

	public void approveComments(String userName, Integer siteId, SummaryComment summaryComment) throws SummaryException;

}
