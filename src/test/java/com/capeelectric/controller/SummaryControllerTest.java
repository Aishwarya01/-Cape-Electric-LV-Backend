
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

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.service.impl.SummaryServiceImpl;

@ExtendWith(SpringExtension.class)

@ExtendWith(MockitoExtension.class)
public class SummaryControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(SummaryControllerTest.class);

	@InjectMocks
	private SummaryController summaryController;

	@MockBean
	private SummaryServiceImpl summaryServiceImpl;

	@MockBean
	private SummaryException summaryException;

	private Summary summary;

	{
		summary = new Summary();
		summary.setUserName("LVsystem@gmail.com");
		summary.setSiteId(12);
	}

	@Test
	public void testAddSummary() throws SummaryException {
		logger.info("testAddSummary Function Started");

		doNothing().when(summaryServiceImpl).addSummary(summary);
		ResponseEntity<String> addSummary = summaryController.addSummary(summary);
		assertEquals(addSummary.getBody(), "successfully added Summary");

		logger.info("testAddSummary Function Ended");
	}

	@Test
	public void testRetrieveSummary() throws SummaryException {
		List<Summary> arrayList = new ArrayList<Summary>();
		arrayList.add(summary);
		
		logger.info("testRetrieveSummary Function Started");

		when(summaryServiceImpl.retrieveSummary("LVsystem@gmail.com", 12)).thenReturn(arrayList);
		ResponseEntity<List<Summary>> retrieveSummary = summaryController.retrieveSummary("LVsystem@gmail.com", 12);
		assertEquals( HttpStatus.OK, retrieveSummary.getStatusCode());

		logger.info("testRetrieveSummary Function Ended");

	}
}
