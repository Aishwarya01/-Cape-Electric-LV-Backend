package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.service.impl.SupplyCharacteristicsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SupplyCharacteristicsControllerTest {

	@MockBean
	private SupplyCharacteristicsServiceImpl supplyCharacteristicsServiceImpl;

	@InjectMocks
	private SupplyCharacteristicsController supplyCharacteristicsController;

	@MockBean
	private SupplyCharacteristicsException supplyCharacteristicsException;

	private SupplyCharacteristics supplyCharacteristics;

	{
		supplyCharacteristics = new SupplyCharacteristics();
		supplyCharacteristics.setSupplyCharacteristicsId(1);
		supplyCharacteristics.setUserName("cape");
		supplyCharacteristics.setSiteId(1);
	}

	@Test
	public void testAddCharacteristics() throws SupplyCharacteristicsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = supplyCharacteristicsController
				.addCharacteristics(supplyCharacteristics);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
	}

	@Test
	public void testRetrieveCharacteristics() throws SupplyCharacteristicsException {
		ResponseEntity<List<SupplyCharacteristics>> expectedResponseEntity = new ResponseEntity<List<SupplyCharacteristics>>(
				HttpStatus.OK);
		ResponseEntity<List<SupplyCharacteristics>> actualResponseEntity = supplyCharacteristicsController
				.retrieveCharacteristics(supplyCharacteristics.getUserName(), supplyCharacteristics.getSiteId());
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
	}
}
