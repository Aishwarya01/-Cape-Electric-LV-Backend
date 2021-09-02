package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;

public interface InstalReportPDFService {

	public List<ReportDetails> printBasicInfromation(String userName, Integer SiteId) throws InstalReportException;

}