//package com.capeelectric.controller;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.capeelectric.exception.SupplyCharacteristicsException;
//import com.capeelectric.model.SupplyCharacteristics;
//import com.capeelectric.service.impl.PrintSupplyServiceImpl;
//import com.capeelectric.service.impl.SupplyCharacteristicsServiceImpl;
//
//@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//public class PrintSupplyController {
//	
//	
//	@MockBean
//	private PrintSupplyServiceImpl printSupplyServiceImpl;
//
//	@InjectMocks
//	private PrintSupplyController  printSupplyController;
//
//	@MockBean
//	private SupplyCharacteristicsException supplyCharacteristicsException;
//
//	private SupplyCharacteristics supplyCharacteristics;
//
//	{
//		supplyCharacteristics = new SupplyCharacteristics();
//		supplyCharacteristics.setSupplyCharacteristicsId(1);
//		supplyCharacteristics.setUserName("cape");
//		supplyCharacteristics.setSiteId(1);
//	}
//
//	@Test
//	public void testPrintSupply() throws SupplyCharacteristicsException {
//		ResponseEntity<List<SupplyCharacteristics>> expectedResponseEntity = new ResponseEntity<List<SupplyCharacteristics>>(
//				HttpStatus.OK);
//		ResponseEntity<List<SupplyCharacteristics>> actualResponseEntity = printSupplyController
//				.printSupply(supplyCharacteristics.getUserName(), supplyCharacteristics.getSiteId());
//		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
//	}
//
//	private ResponseEntity<List<SupplyCharacteristics>> printSupply(String userName, Integer siteId) {
//	 
//		return null;
//	}
//
//	
//}
