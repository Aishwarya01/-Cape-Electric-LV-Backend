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

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.impl.PeriodicTestingServiceImpl;

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
	public void testTesting_NA_Value() throws PeriodicTestingException {
		logger.info("'NA'value checking processes started");
		ArrayList<Testing> testingList = new ArrayList<Testing>();
		testingList.add(utill());
		testingReport.setTesting(testingList);

		when(testingReportRepository.findBySiteId(1)).thenReturn(Optional.of(testingReport));
		logger.info("Successfully added Summary_Object flow");
		testingReport.setSiteId(2);
		periodicTestingServiceImpl.addTestingReport(testingReport);
		logger.info("'NA'value checking processes started");

	}

	private Testing utill() throws PeriodicTestingException {
		logger.info("Added 'NA' with testing Object");
		
	//	List<TestVoltage> testVoltageList = new ArrayList<TestVoltage>();
	//	List<TestLoopImpedance> loopImpedanceList = new ArrayList<TestLoopImpedance>();

	//	TestVoltage testVoltage = new TestVoltage();
	//	testVoltage.setBnVoltage("122");
	//	testVoltage.setBpeVoltage("na");
	//	testVoltage.setRbVoltage("1212");
	//	testVoltage.setRpeVoltage("NA");

	//	TestLoopImpedance loopImpedance = new TestLoopImpedance();
	//	loopImpedance.setBnLoopImpedance("na");
	//	loopImpedance.setBpeLoopImpedance("12312");
	//	loopImpedance.setRbLoopImpedance("Na");
	//	loopImpedance.setRnLoopImpedance("NA");

	//	loopImpedanceList.add(loopImpedance);
		testing = new Testing();
	//	testing.setTestLoopImpedance(loopImpedanceList);

	//	testVoltageList.add(testVoltage);
	//	testing.setTestVoltage(testVoltageList);
		return testing;
	}

}
