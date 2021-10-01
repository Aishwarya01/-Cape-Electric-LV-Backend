
package com.capeelectric.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.Site;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.FinalReportService;

/**
 * This FinalReportServiceImpl class to doing retrieve_site and
 * retrieve_allFinalinformations based on siteId and userName
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class FinalReportServiceImpl implements FinalReportService {

	private static final Logger logger = LoggerFactory.getLogger(FinalReportServiceImpl.class);

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private InstalReportDetailsRepository instalReportDetailsRepository;

	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@Autowired
	private InspectionRepository inspectionRepository;

	@Autowired
	private TestingReportRepository testingReportRepository;

	@Autowired
	private SummaryRepository summaryRepository;

	private FinalReport finalReport;

	/**
	 * @param userName and departmentName also string retrieveSiteDetails method to
	 *                 retrieve site details based on userName and departmentName
	 * @return List of sites
	 * 
	 */
	@Override
	public List<Site> retrieveListOfSite(String userName) throws FinalReportException {

		if (userName != null) {
			try {
				logger.info("Site fetching process started");
				return siteRepository.findByUserName(userName);
			} catch (Exception e) {
				logger.info("Site fetching process faild");
				throw new FinalReportException("Fetching site process faild");
			}
		} else {
			throw new FinalReportException("Invaild Input");
		}
	}

	/**
	 * @param userName and siteId retrieveFinalReport method to retrieve
	 *                 InstallReport_Information,Supplycharacteristic,PriodicInspection,PriodicTesting
	 *                 and Summary record based on userName & SiteId
	 * @return finalReport model object
	 * 
	 */

	@Override
	public Optional<FinalReport> retrieveFinalReport(String userName, Integer siteId) throws FinalReportException {

		if (userName != null && siteId != null) {
			finalReport = new FinalReport();
			finalReport.setUserName(userName);
			finalReport.setSiteId(siteId);

			logger.debug("fetching process started for InstallReport_Information");
			Optional<ReportDetails> reportDetails = instalReportDetailsRepository.findBySiteId(siteId);
			logger.debug("InstallReport_Information fetching ended");
			if (reportDetails.isPresent() && reportDetails != null) {
				finalReport.setReportDetails(reportDetails.get());

				logger.debug("fetching process started for SupplyCharacteristic");
				Optional<SupplyCharacteristics> supplyCharacteristics = supplyCharacteristicsRepository
						.findBySiteId(siteId);
				logger.debug("SupplyCharacteristic_fetching ended");
				if (supplyCharacteristics.isPresent() && supplyCharacteristics != null) {
					finalReport.setSupplyCharacteristics(supplyCharacteristics.get());

					logger.debug("fetching process started for PriodicInspection");
					Optional<PeriodicInspection> periodicInspection = inspectionRepository.findBySiteId(siteId);
					logger.debug("PriodicInspection_fetching ended");

					if (periodicInspection.isPresent() && periodicInspection != null) {
						finalReport.setPeriodicInspection(periodicInspection.get());

						logger.debug("fetching process started for PriodicTesting");
						Optional<TestingReport> testingReport = testingReportRepository.findBySiteId(siteId);
						logger.debug("PriodicTesting_fetching ended");

						if (testingReport.isPresent() && testingReport != null) {
							finalReport.setTestingReport(testingReport.get());

							logger.debug("fetching process started for Summary");
							Optional<Summary> summary = summaryRepository.findBySiteId(siteId);
							logger.debug("Summary_fetching ended");

							if (summary.isPresent() && summary != null) {
								finalReport.setSummary(summary.get());

								logger.debug("Successfully Five_Steps fetching Operation done");
								return Optional.of(finalReport);

							}
						}
					}
				}
			}

			return Optional.of(finalReport);

		} else {
			throw new FinalReportException("Invalid Input");
		}
	}

}
