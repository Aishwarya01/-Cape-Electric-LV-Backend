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

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.service.impl.InspectionServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InspectionControllerTest {

	@MockBean
	private InspectionServiceImpl inspectionServiceImpl;

	@InjectMocks
	private InspectionController inspectionController;

	@MockBean
	private InspectionException inspectionException;

	private PeriodicInspection periodicInspection;

	{
		periodicInspection = new PeriodicInspection();
		periodicInspection.setUserName("cape");
		periodicInspection.setSiteId(1);
	}

	@Test
	public void testAddInspectionDetails() throws InspectionException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.CREATED);
		ResponseEntity<String> actualResponseEntity = inspectionController.addInspectionDetails(periodicInspection);
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
	}

	@Test
	public void testRetrieveInspectionDetails() throws InspectionException {
		ResponseEntity<List<PeriodicInspection>> expectedResponseEntity = new ResponseEntity<List<PeriodicInspection>>(HttpStatus.OK);
		ResponseEntity<List<PeriodicInspection>> actualResponseEntity = inspectionController
				.retrieveInspectionDetails(periodicInspection.getUserName(), periodicInspection.getSiteId());
		assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());
	}

}