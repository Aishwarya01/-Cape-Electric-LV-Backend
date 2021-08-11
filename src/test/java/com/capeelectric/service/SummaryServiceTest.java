
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

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.impl.SummaryServiceImpl;

@ExtendWith(SpringExtension.class)

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SummaryServiceTest.class);

	@MockBean
	private SummaryRepository summaryRepository;

	@InjectMocks
	private SummaryServiceImpl summaryServiceImpl;

	private Summary summary;

	{
		summary = new Summary();
		summary.setUserName("LVsystem@gmail.com");
		summary.setSiteId(12);
	}

	@Test
	public void testAddSummary() throws SummaryException {
		 
		when(summaryRepository.findBySiteId(12)).thenReturn(Optional.of(summary));

		logger.info("SiteId already Present_flow");
		SummaryException summaryException_1 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.addSummary(summary));
		assertEquals(summaryException_1.getMessage(), "Given SiteId already present");

		logger.info("Successfully added Summary_Object flow");
		summary.setSiteId(1);
		summaryServiceImpl.addSummary(summary);

		logger.info("Invalid Present_flow");
		summary.setUserName(null);
		SummaryException summaryException_2 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.addSummary(summary));
		assertEquals(summaryException_2.getMessage(), "UserName and SiteId are Invalid Inputs");

	}

	@Test
	public void testRetrieveSummary() throws SummaryException {
		
		List<Summary> arrayList = new ArrayList<Summary>();
		arrayList.add(summary);
		when(summaryRepository.findByUserNameAndSiteId("LVsystem@gmail.com", 12)).thenReturn(arrayList);

		logger.info("SuccessFlow of Retrieve Summary Obeject");
		summaryServiceImpl.retrieveSummary("LVsystem@gmail.com", 12);

		logger.info("Invalid Input flow");
		SummaryException summaryException = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.retrieveSummary(null, 12));
		assertEquals(summaryException.getMessage(), "UserName and SiteId are Invalid Inputs");

	}

}
