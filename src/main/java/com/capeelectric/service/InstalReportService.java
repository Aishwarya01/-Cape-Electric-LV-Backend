package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;

/**
 **
  * @author capeelectricsoftware
 *
 */
public interface InstalReportService {

	public void addInstallationReport(ReportDetails reportDetails) throws InstalReportException;

	public List<ReportDetails> retrieveInstallationReport(String userName, Integer SiteId) throws InstalReportException;

}
