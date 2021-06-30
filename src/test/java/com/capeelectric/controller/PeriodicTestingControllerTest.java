package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.TestingReport;
import com.capeelectric.service.impl.PeriodicTestingServiceImpl;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class PeriodicTestingControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(PeriodicTestingControllerTest.class);

	@InjectMocks
	private PeriodicTestingController periodicTestingController;

	@MockBean
	private PeriodicTestingServiceImpl periodicTestingServiceImpl;

	@MockBean
	private PeriodicTestingException periodicTestingException;

	private TestingReport testingReport;

	{
		testingReport = new TestingReport();
		testingReport.setSiteId(1);
		testingReport.setUserName("LVsystem@gmail.com");
	}

	@Test
	public void testAddTestingReport() throws PeriodicTestingException {
		logger.info("testAddTestingReport Function Started");

		doNothing().when(periodicTestingServiceImpl).addTestingReport(testingReport);
		ResponseEntity<String> addTestingReport = periodicTestingController.addTestingReport(testingReport);
		assertEquals(addTestingReport.getBody(), "successfully added TestingReport");

		logger.info("testAddTestingReport Function Ended");
	}

	@Test
	public void testRetrieveTestingReport() throws PeriodicTestingException {
		List<TestingReport> arrayList = new ArrayList<>();
		arrayList.add(testingReport);

		logger.info("testRetrieveTestingReport Function Started");

		when(periodicTestingServiceImpl.retrieveTestingReport("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<TestingReport>> retrieveTestingReport = periodicTestingController
				.retrieveTestingReport("LVsystem@gmail.com", 12);
		assertEquals(HttpStatus.OK, retrieveTestingReport.getStatusCode());

		logger.info("testRetrieveTestingReport Function Ended");

	}

}
