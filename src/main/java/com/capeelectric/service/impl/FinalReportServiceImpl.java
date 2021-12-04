
package com.capeelectric.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.BoundingLocationReport;
import com.capeelectric.model.EarthingLocationReport;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.InstalLocationReport;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.Site;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingRecords;
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

					supplyCharacteristics.get()
							.setInstalLocationReport(findNonRemovedInstallLocation(supplyCharacteristics.get()));
					supplyCharacteristics.get()
							.setBoundingLocationReport(findNonRemovedBondingLocation(supplyCharacteristics.get()));
					supplyCharacteristics.get()
							.setEarthingLocationReport(findNonRemovedEarthingLocation(supplyCharacteristics.get()));
					finalReport.setSupplyCharacteristics(supplyCharacteristics.get());

					logger.debug("fetching process started for PriodicInspection");
					Optional<PeriodicInspection> periodicInspection = inspectionRepository.findBySiteId(siteId);
					logger.debug("PriodicInspection_fetching ended");

					if (periodicInspection.isPresent() && periodicInspection != null) {
						
						periodicInspection.get()
						.setIpaoInspection(findNonRemovedInspectionLocation(periodicInspection.get()));
						finalReport.setPeriodicInspection(periodicInspection.get());

						logger.debug("fetching process started for PriodicTesting");
						Optional<TestingReport> testingReport = testingReportRepository.findBySiteId(siteId);
						logger.debug("PriodicTesting_fetching ended");

						if (testingReport.isPresent() && testingReport != null) {
							testingReport.get().setTesting(findNonRemoveTesting(testingReport.get().getTesting()));
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

	private List<IpaoInspection> findNonRemovedInspectionLocation(PeriodicInspection inspectionRepo) {

		ArrayList<IpaoInspection>inspectionReport = new ArrayList<IpaoInspection>();
		List<IpaoInspection> findNonRemoveLocation = inspectionRepo.getIpaoInspection();
		for (IpaoInspection inspectionLocationReport : findNonRemoveLocation) {
			if (!inspectionLocationReport.getInspectionFlag().equalsIgnoreCase("R")) {
				inspectionReport.add(inspectionLocationReport);
			}
		}
		return inspectionReport;
	}

	@Override
	public List<Site> retrieveAllSites() throws FinalReportException {
		return (List<Site>)siteRepository.findAll();
	}

	
	private List<InstalLocationReport> findNonRemovedInstallLocation(SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<InstalLocationReport> locationReport = new ArrayList<InstalLocationReport>();
		List<InstalLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getInstalLocationReport();
		for (InstalLocationReport instalLocationReport : findNonRemoveLocation) {
			if (!instalLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				locationReport.add(instalLocationReport);
			}
		}
		return locationReport;
	}
	
	private List<BoundingLocationReport> findNonRemovedBondingLocation(
			SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<BoundingLocationReport> locationReport = new ArrayList<BoundingLocationReport>();
		List<BoundingLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getBoundingLocationReport();
		for (BoundingLocationReport bondingLocationReport : findNonRemoveLocation) {
			if (!bondingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				locationReport.add(bondingLocationReport);
			}
		}
		return locationReport;
	}
	
	private List<EarthingLocationReport> findNonRemovedEarthingLocation(
			SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<EarthingLocationReport> locationReport = new ArrayList<EarthingLocationReport>();
		List<EarthingLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getEarthingLocationReport();
		for (EarthingLocationReport earthingLocationReport : findNonRemoveLocation) {
			if (!earthingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				locationReport.add(earthingLocationReport);
			}
		}
		return locationReport;
	}
	
	private List<Testing> findNonRemoveTesting(List<Testing> listOfTesting) {
		for (Testing testing : listOfTesting) {
			if (testing != null && testing.getTestingStatus().equalsIgnoreCase("R")) {
				listOfTesting.remove(testing);
			} else {
				testing.setTestingRecords(findNonRemoveTestingRecord(testing.getTestingRecords()));
			}
		}
		return listOfTesting;
	}

	private List<TestingRecords> findNonRemoveTestingRecord(List<TestingRecords> listOfTestingRecords) {
		List<TestingRecords> listNonRemovedTestingRecord = new ArrayList<TestingRecords>();
		for (TestingRecords testingRecords : listOfTestingRecords) {
			if (testingRecords.getTestingRecordStatus().equalsIgnoreCase("R")) {
				listNonRemovedTestingRecord.add(testingRecords);
			}
		}
		return listNonRemovedTestingRecord;
	}
}

