
package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
import com.capeelectric.exception.SummaryException;
import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Register;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryComment;
import com.capeelectric.model.SupplyCharacteristicComment;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.impl.SummaryServiceImpl;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(SummaryServiceTest.class);

	@MockBean
	private SummaryRepository summaryRepository;

	@InjectMocks
	private SummaryServiceImpl summaryServiceImpl;
	
	@MockBean
	private UserFullName userFullName;

	private Summary summary;
	
	private SummaryComment summaryComment;
	
	@MockBean
	private SiteRepository siteRepository;
	
	private ArrayList<SummaryComment> listOfComments;
	
    private Site site;
	
	private Register register;

	{
		summary = new Summary();
		summary.setUserName("LVsystem@gmail.com");
		summary.setSiteId(12);
		summary.setSummaryId(1);
		
		summaryComment = new SummaryComment();
		summaryComment.setViewerDate(LocalDateTime.now());
		ArrayList<SummaryComment> listComment = new ArrayList<SummaryComment>();
		listComment.add(summaryComment);
		summary.setSummaryComment(listComment);
		
	}
	
	{
		register =new Register();
		register.setUsername("cape");
		
		site = new Site();
		site.setUserName("Inspector@gmail.com");
		site.setSiteId(1);
		site.setSite("user");
		site.setAssignedTo("Viewer@gmail.com");

		SitePersons sitePersons1 =new SitePersons();
		sitePersons1.setPersonId(1);
		sitePersons1.setSiteName("user");
		sitePersons1.setPersonInchargeEmail("Viewer@gmail.com");
		sitePersons1.setInActive(true);
		 
		HashSet sitePersonsSet = new HashSet<SitePersons>();
		sitePersonsSet.add(sitePersons1);
		site.setSitePersons(sitePersonsSet);
	}

	@Test
	public void testAddSummary() throws SummaryException {

		when(summaryRepository.findBySiteId(12)).thenReturn(Optional.of(summary));

		logger.info("SiteId already Present_flow");
		SummaryException summaryException_1 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.addSummary(summary));
		assertEquals(summaryException_1.getMessage(), "Site-Id Already Available");

		logger.info("Successfully added Summary_Object flow");
		summary.setSiteId(1);

		SummaryException summaryException_2 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.addSummary(summary));
		assertEquals(summaryException_2.getMessage(), "Site-Id Information not Available in site_Table");
		
		when(siteRepository.findById(1)).thenReturn(Optional.of(site));
		summaryServiceImpl.addSummary(summary);

		logger.info("Invalid Present_flow");
		summary.setUserName(null);
		SummaryException summaryException_3 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.addSummary(summary));
		assertEquals(summaryException_3.getMessage(), "Invalid Inputs");

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
		assertEquals(summaryException.getMessage(), "Invalid Inputs");

	}

	
	@Test
	public void testUpdateSummary() throws DecimalConversionException, SummaryException {
		summary.setUserName("LVsystem@gmail.com");
		when(summaryRepository.findById(1)).thenReturn(Optional.of(summary));
		summaryServiceImpl.updateSummary(summary);
		
		Summary summary_1 = new Summary();
		summary_1.setSiteId(12);
		summary_1.setUserName("cape");
		summary_1.setSummaryId(12);
		
		when(summaryRepository.findById(4)).thenReturn(Optional.of(summary));
		SummaryException assertThrows = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.updateSummary(summary_1));
		
		assertEquals(assertThrows.getMessage(),"Given SiteId and ReportId is Invalid");
		
		summary.setSiteId(null);
		when(summaryRepository.findById(2)).thenReturn(Optional.of(summary));
		SummaryException assertThrows_1 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.updateSummary(summary));
		
		assertEquals(assertThrows_1.getMessage(),"Invalid inputs");
	}
	

	
	@Test
	public void testSendComments() throws SummaryException {
		listOfComments = new ArrayList<SummaryComment>();
		when(summaryRepository.findBySiteId(1)).thenReturn(Optional.of(summary));
		when(siteRepository.findById(1)).thenReturn(Optional.of(site));
		summaryServiceImpl.sendComments("Viewer@gmail.com", 1, summaryComment);
 
		summaryComment.setCommentsId(1);
		listOfComments.add(summaryComment);
		summary.setSummaryComment(listOfComments);
		when(summaryRepository.findBySiteId(1)).thenReturn(Optional.of(summary));
		summaryServiceImpl.sendComments("Viewer@gmail.com", 1, summaryComment);

		listOfComments.remove(summaryComment);
		summary.setSummaryComment(listOfComments);
		summaryServiceImpl.sendComments("Viewer@gmail.com", 1, summaryComment);

		SummaryException assertThrows = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.sendComments("Inspector@gmail.com", 1, summaryComment));

		assertEquals(assertThrows.getMessage(), "Given userName not allowing for SEND comment");
	}
	
	@Test
	public void testReplyComments() throws SummaryException {
		listOfComments = new ArrayList<SummaryComment>();
		summaryComment.setCommentsId(1);
		listOfComments.add(summaryComment);
		summary.setUserName("Inspector@gmail.com");
		summary.setSummaryComment(listOfComments);
		when(summaryRepository.findBySiteId(1)).thenReturn(Optional.of(summary));
		when(siteRepository.findById(1)).thenReturn(Optional.of(site));
		summaryServiceImpl.replyComments("Inspector@gmail.com", 1, summaryComment);

		SummaryException assertThrows = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.replyComments("Viewer@gmail.com", 1, summaryComment));

		assertEquals(assertThrows.getMessage(), "Given userName not allowing for REPLY comment");
	}
	
	@Test
	public void testApproveComments() throws SummaryException {
		listOfComments = new ArrayList<SummaryComment>();
		summaryComment.setCommentsId(1);
		listOfComments.add(summaryComment);
		summary.setSummaryComment(listOfComments);
		when(summaryRepository.findBySiteId(1)).thenReturn(Optional.of(summary));
		when(siteRepository.findById(1)).thenReturn(Optional.of(site));
		summaryServiceImpl.approveComments("Viewer@gmail.com", 1, summaryComment);
		
		SummaryException assertThrows = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.approveComments("Inspector@gmail.com", 1, summaryComment));
		
		assertEquals(assertThrows.getMessage(), "Given userName not allowing for APPROVED comment");
	}
	
	@Test
	public void testComments_Exception() throws SummaryException {

		summary.setUserName(null); 
		when(summaryRepository.findBySiteId(2)).thenReturn(Optional.of(summary));
		when(siteRepository.findById(1)).thenReturn(Optional.of(site));

		SummaryException assertThrows_1 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.sendComments("Viewer@gmail.com", 1, summaryComment));

		assertEquals(assertThrows_1.getMessage(), "Given username not have access for comments");

		summary.setSummaryComment(null);
		SummaryException assertThrows_2 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.sendComments("Viewer@gmail.com", 2, summaryComment));
		assertEquals(assertThrows_2.getMessage(), "Siteinformation doesn't exist, try with different Site-Id");

		SummaryException assertThrows_3 = Assertions.assertThrows(SummaryException.class,
				() -> summaryServiceImpl.sendComments("Viewer@gmail.com", null, summaryComment));
	//	assertEquals(assertThrows_3.getMessage(), "Invalid Inputs");

	}

}
