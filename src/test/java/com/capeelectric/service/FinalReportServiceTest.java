package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.FinalReportException;
import com.capeelectric.model.FinalReport;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.Site;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.Testing;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.repository.TestInfoRepository;
import com.capeelectric.service.impl.FinalReportServiceImpl;

/**
 * @author capeelectricsoftware
 *
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class FinalReportServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SummaryServiceTest.class);

	@InjectMocks
	private FinalReportServiceImpl finalReportServiceImpl;

	@MockBean
	private SiteRepository siteRepository;

	@MockBean
	private InstalReportDetailsRepository instalReportDetailsRepository;

	@MockBean
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@MockBean
	private InspectionRepository inspectionRepository;

	@MockBean
	private TestInfoRepository testInfoRepository;

	@MockBean
	private SummaryRepository summaryRepository;

	private Site site;

	private FinalReport finalReport;

	{
		finalReport = new FinalReport();
		finalReport.setUserName("LVsystem@gmail.com");
		finalReport.setSiteId(1);

		finalReport.setReportDetails(retrieveReportDetails());
		finalReport.setSupplyCharacteristics(retrieveSupplyCharacteristics());
		finalReport.setIpaoInspection(retrieveIpaoInspection());
		finalReport.setTesting(retrieveTesting());
		finalReport.setSummary(retrieveSummary());

	}

	{
		site = new Site();
		site.setUserName("LVsystem@gmail.com");
		site.setSiteId(1);
	}

	@Test
	public void testRetriveListOfSite() throws FinalReportException {
		logger.info("testRetriveListOfSite method started");
		ArrayList<Site> sites = new ArrayList<Site>();
		sites.add(site);
		when(siteRepository.findByClientNameAndDepartmentName("LVsystem@gmail.com", "IT")).thenReturn(sites);

		List<Site> retrieveListOfSite = finalReportServiceImpl.retrieveListOfSite("LVsystem@gmail.com", "IT");
		assertTrue(retrieveListOfSite.contains(site));

		FinalReportException finalReportException = Assertions.assertThrows(FinalReportException.class,
				() -> finalReportServiceImpl.retrieveListOfSite(null, "IT"));
		assertEquals(finalReportException.getMessage(), "Invaild Input");
		logger.info("testRetriveListOfSite method ended");

	}

	@Test
	public void testRetriveFinalReport() throws FinalReportException {
		logger.info("testRetriveListOfSite method started");
		ArrayList<ReportDetails> reportDetailsList_1 = new ArrayList<ReportDetails>();
		reportDetailsList_1.add(retrieveReportDetails());
		when(instalReportDetailsRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 1))
				.thenReturn(reportDetailsList_1);

		ArrayList<SupplyCharacteristics> SupplyCharacteristics = new ArrayList<>();
		SupplyCharacteristics.add(retrieveSupplyCharacteristics());
		when(supplyCharacteristicsRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 1))
				.thenReturn(SupplyCharacteristics);

		ArrayList<IpaoInspection> inspection = new ArrayList<>();
		inspection.add(retrieveIpaoInspection());
		when(inspectionRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 1)).thenReturn(inspection);

		ArrayList<Testing> testing = new ArrayList<>();
		testing.add(retrieveTesting());
		when(testInfoRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 1)).thenReturn(testing);

		ArrayList<Summary> summary = new ArrayList<>();
		summary.add(retrieveSummary());
		when(summaryRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 1)).thenReturn(summary);

		Optional<FinalReport> retrieveFinalReport = finalReportServiceImpl.retrieveFinalReport("LVsystem@gmail.com", 1);
		assertNotNull(retrieveFinalReport);

		FinalReportException finalReportException = Assertions.assertThrows(FinalReportException.class,
				() -> finalReportServiceImpl.retrieveFinalReport(null, 1));
		assertEquals(finalReportException.getMessage(), "Invalid Input");
		logger.info("testRetriveListOfSite method ended");

	}

	private ReportDetails retrieveReportDetails() {
		ReportDetails reportDetails = new ReportDetails();
		reportDetails.setUserName("LVsystem@gmail.com");
		reportDetails.setSiteId(1);
		return reportDetails;
	}

	private SupplyCharacteristics retrieveSupplyCharacteristics() {
		SupplyCharacteristics supplyCharacteristics = new SupplyCharacteristics();
		supplyCharacteristics.setUserName("LVsystem@gmail.com");
		supplyCharacteristics.setSiteId(1);
		return supplyCharacteristics;
	}

	private IpaoInspection retrieveIpaoInspection() {
		IpaoInspection ipaoInspection = new IpaoInspection();
		ipaoInspection.setUserName("LVsystem@gmail.com");
		ipaoInspection.setSiteId(1);
		return ipaoInspection;
	}

	private Testing retrieveTesting() {
		Testing testing = new Testing();
		testing.setUserName("LVsystem@gmail.com");
		testing.setSiteId(1);
		return testing;
	}

	private Summary retrieveSummary() {
		Summary summary = new Summary();
		summary.setUserName("LVsystem@gmail.com");
		summary.setSiteId(1);
		return summary;
	}
}
