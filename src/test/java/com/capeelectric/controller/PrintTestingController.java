//package com.capeelectric.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.capeelectric.exception.PeriodicTestingException;
//import com.capeelectric.model.TestingReport;
//import com.capeelectric.service.PrintTestingService;
//import com.capeelectric.service.impl.PeriodicTestingServiceImpl;
//import com.capeelectric.service.impl.PrintTestingServiceImpl;
//
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//public class PrintTestingController {
//	private static final Logger logger = LoggerFactory.getLogger(PeriodicTestingControllerTest.class);
//
//	@InjectMocks
//	private PrintTestingController printTestingController;
//
//	@MockBean
//	private PrintTestingServiceImpl printTestingService;
//
//	@MockBean
//	private PeriodicTestingException periodicTestingException;
//
//	private TestingReport testingReport;
//
//	{
//		testingReport = new TestingReport();
//		testingReport.setSiteId(1);
//		testingReport.setUserName("LVsystem@gmail.com");
//	}
//	
//	
//	@Test
//	public void testPrintTesting() throws PeriodicTestingException {
//		List<TestingReport> arrayList = new ArrayList<>();
//		arrayList.add(testingReport);
//
//		logger.info("testRetrievePeriodicTesting Function Started");
//
////	when(printTestingService.printTesting("LVsystem@gmail.com", 12));
//		ResponseEntity<List<TestingReport>> printTesting = printTestingController
//				.printTesting("LVsystem@gmail.com", 12);
//		assertEquals(HttpStatus.OK, printTesting.getStatusCode());
//
//		logger.info("testRetrievePeriodicTesting Function Ended");
//
//	}
//
//
//	private ResponseEntity<List<TestingReport>> printTesting(String string, int i) {
//		return null;
//	}
//
//
// 
//}
