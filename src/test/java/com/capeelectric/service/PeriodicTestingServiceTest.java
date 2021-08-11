package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.capeelectric.exception.DecimalConversionException;
import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingRecords;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.impl.PeriodicTestingServiceImpl;
import com.capeelectric.util.UserFullName;

/**
 * 
 * @author capeelectricsoftware
 * @param <E>
 *
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class PeriodicTestingServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(PeriodicTestingServiceTest.class);

	@MockBean
	private TestingReportRepository testingReportRepository;

	@InjectMocks
	private PeriodicTestingServiceImpl periodicTestingServiceImpl;
	
	@MockBean
	private UserFullName userFullName;

	private TestingReport testingReport;
	
	private Testing testing;
	
	{
		testingReport = new TestingReport();
		testingReport.setSiteId(1);
		testingReport.setUserName("LVsystem@gmail.com");
	}
	
	@Test
	public void testAddTestingReport() throws PeriodicTestingException {
		 
		when(testingReportRepository.findBySiteId(1)).thenReturn(Optional.of(testingReport));

		logger.info("SiteId already Present_flow");
		PeriodicTestingException periodicTestingException_1 = Assertions.assertThrows(PeriodicTestingException.class,
				() -> periodicTestingServiceImpl.addTestingReport(testingReport));
		assertEquals(periodicTestingException_1.getMessage(), "SiteId Already Present");

		when(testingReportRepository.findBySiteId(1)).thenReturn(Optional.of(testingReport));
		logger.info("Successfully added Summary_Object flow");
		testingReport.setSiteId(2);
		periodicTestingServiceImpl.addTestingReport(testingReport);

		logger.info("Invalid Present_flow");
		testingReport.setUserName(null);
		PeriodicTestingException periodicTestingException_2 = Assertions.assertThrows(PeriodicTestingException.class,
				() -> periodicTestingServiceImpl.addTestingReport(testingReport));
		assertEquals(periodicTestingException_2.getMessage(), "UserName and SiteId Invalid Input");

	}

	@Test
	public void testRetrieveTestingReport() throws PeriodicTestingException {
		
		List<TestingReport> arrayList = new ArrayList<TestingReport>();
		arrayList.add(testingReport);
		when(testingReportRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve Summary Obeject");
		periodicTestingServiceImpl.retrieveTestingReport("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		PeriodicTestingException periodicTestingException = Assertions.assertThrows(PeriodicTestingException.class,
				() -> periodicTestingServiceImpl.retrieveTestingReport(null, 12));
		assertEquals(periodicTestingException.getMessage(), "UserName and SiteId Invalid Input");

	}

	@Test
	public void testTesting_NA_Value() throws DecimalConversionException, PeriodicTestingException {
		logger.info("'NA'value checking processes started");
		ArrayList<Testing> testingList = new ArrayList<Testing>();
		testingList.add(utill_withDemcimal_Records());
		testingReport.setTesting(testingList);

		when(testingReportRepository.findBySiteId(1)).thenReturn(Optional.of(testingReport));
		logger.info("Successfully added Summary_Object flow");
		testingReport.setSiteId(2);
		periodicTestingServiceImpl.addTestingReport(testingReport);
		
		utill_withOutDemcimal_Records();		
 		 
		logger.info("'NA'value checking processes started");

	}

	private Testing utill_withDemcimal_Records() throws DecimalConversionException {
		logger.info("Demcimal_Conversion success case");
		List<TestingRecords> testingRecordsList = new ArrayList<TestingRecords>();

		TestingRecords records = new TestingRecords();
		records.setTestVoltage("212,na,21,534,212");
		records.setTestLoopImpedance("na,12423,413,na");
		records.setTestFaultCurrent("312,122,na,234");

		testingRecordsList.add(records);
		testing = new Testing();
		testing.setTestingRecords(testingRecordsList);

		return testing;
	}

	private Testing utill_withOutDemcimal_Records() throws DecimalConversionException {
		logger.info("Demcimal_Conversion faild case");
		TestingRecords testingRecords = new TestingRecords();

		DecimalConversionException decimalConversionException = Assertions
				.assertThrows(DecimalConversionException.class, () -> testingRecords.setTestVoltage(""));

		assertEquals(decimalConversionException.getMessage(), "invalid input of value for DecimalConversion");
		return null;
	}
	
	@Test
	public void testUpdateTesting() throws DecimalConversionException, PeriodicTestingException {
		testingReport.setUserName("LVsystem@gmail.com");
		testingReport.setTestingReportId(1);
		when(testingReportRepository.findById(1)).thenReturn(Optional.of(testingReport));
		periodicTestingServiceImpl.updatePeriodicTesting(testingReport);

		TestingReport testingReport_1 = new TestingReport();
		testingReport_1.setSiteId(12);
		testingReport_1.setUserName("cape");
		testingReport_1.setTestingReportId(12);

		when(testingReportRepository.findById(4)).thenReturn(Optional.of(testingReport));
		PeriodicTestingException assertThrows = Assertions.assertThrows(PeriodicTestingException.class,
				() -> periodicTestingServiceImpl.updatePeriodicTesting(testingReport_1));
		assertEquals(assertThrows.getMessage(), "Given SiteId and ReportId is Invalid");

		testingReport.setSiteId(null);
		when(testingReportRepository.findById(2)).thenReturn(Optional.of(testingReport));
		PeriodicTestingException assertThrows_1 = Assertions.assertThrows(PeriodicTestingException.class,
				() -> periodicTestingServiceImpl.updatePeriodicTesting(testingReport));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}

}
