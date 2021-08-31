
package com.capeelectric.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.Site;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.FinalReportService;


/**
 * This FinalReportServiceImpl class to doing retrieve_site and retrieve_allFinalinformations based on siteId and userName
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
	 * @param userName and departmentName also string
	 * retrieveSiteDetails method to retrieve site details based on userName and departmentName
	 * @return List of sites
	 * 
	 */
	@Override
	public List<Site> retrieveListOfSite(String userName, String departmentName) throws FinalReportException {

		if (userName != null && departmentName != null) {
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
	 * @param userName and siteId  
	 * retrieveFinalReport method to retrieve  InstallReport_Information,Supplycharacteristic,PriodicInspection,PriodicTesting and Summary record based on userName & SiteId
	 * @return finalReport model object
	 * 
	 */
	
	@Override
	public Optional<FinalReport> retrieveFinalReport(String userName, Integer siteId) throws FinalReportException {
		if (userName != null && siteId != null) {
		finalReport = new FinalReport();
		finalReport.setUserName(userName);
		finalReport.setSiteId(siteId);

		try {

			logger.info("fetching process started for InstallReport_Information");
			finalReport
					.setReportDetails(instalReportDetailsRepository.findByUserNameAndSiteId(userName, siteId).get(0));
			logger.info("InstallReport_Information fetching ended");

			logger.info("fetching process started for SupplyCharacteristic");
			finalReport.setSupplyCharacteristics(
					supplyCharacteristicsRepository.findByUserNameAndSiteId(userName, siteId).get(0));
			logger.info("SupplyCharacteristic_fetching ended");

			logger.info("fetching process started for PriodicInspection");
			finalReport.setPeriodicInspection(inspectionRepository.findByUserNameAndSiteId(userName, siteId).get(0));
			logger.info("PriodicInspection_fetching ended");

			logger.info("fetching process started for PriodicTesting");
			finalReport.setTestingReport(testingReportRepository.findByUserNameAndSiteId(userName, siteId).get(0));
			logger.info("PriodicTesting_fetching ended");

			logger.info("fetching process started for Summary");
			finalReport.setSummary(summaryRepository.findByUserNameAndSiteId(userName, siteId).get(0));
			logger.info("Summary_fetching ended");

			return Optional.of(finalReport);

		}

		catch (Exception e) {
			logger.info("FinalReport fetching process faild");
			throw new FinalReportException("Fetching process faild for fainalReport");
		}

	} else {
		throw new FinalReportException("Invalid Input");
	}

}

}
