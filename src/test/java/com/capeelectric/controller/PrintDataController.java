//package com.capeelectric.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.capeelectric.exception.SummaryException;
//import com.capeelectric.model.Summary;
//import com.capeelectric.service.impl.PrintServiceImpl;
//import com.capeelectric.service.impl.SummaryServiceImpl;
//
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//public class PrintDataController {
//	private static final Logger logger = LoggerFactory.getLogger(SummaryControllerTest.class);
//
//	@InjectMocks
//	private PrintDataController printDataController;
//
//	@MockBean
//	private PrintServiceImpl printServiceImpl;
//
//	@MockBean
//	private SummaryException summaryException;
//
//	private Summary summary;
//
//	{
//		summary = new Summary();
//		summary.setUserName("LVsystem@gmail.com");
//		summary.setSiteId(12);
//	}
//
//	@Test
//	public void testPrintDataController() throws SummaryException {
//		List<Summary> arrayList = new ArrayList<Summary>();
//		arrayList.add(summary);
//		
//		logger.info("testRetrieveSummary Function Started");
//
//	//	Mockito.when(printServiceImpl.printSummary("LVsystem@gmail.com", 12)).thenReturn(arrayList);
//		ResponseEntity<List<Summary>> printSummary = printDataController.printSummary("LVsystem@gmail.com", 12);
//		assertEquals( HttpStatus.OK, printSummary.getStatusCode());
//
//		logger.info("testRetrieveSummary Function Ended");
//
//	}
//
//	private Object when(Object printSummary) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private ResponseEntity<List<Summary>> printSummary(String string, int i) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
