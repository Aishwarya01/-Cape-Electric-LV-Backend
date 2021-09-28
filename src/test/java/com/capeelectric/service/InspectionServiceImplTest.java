package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.service.impl.InspectionServiceImpl;
import com.capeelectric.util.UserFullName;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InspectionServiceImplTest {

	@InjectMocks
	private InspectionServiceImpl inspectionServiceImpl;

	@MockBean
	private InspectionRepository inspectionRepository;

	@MockBean
	private InspectionException inspectionException;
	
	@MockBean
	private UserFullName userFullName;

	private PeriodicInspection periodicInspection;
	
	private PeriodicInspectionComment periodicInspectionComment;

	{
		periodicInspection = new PeriodicInspection();
		periodicInspection.setUserName("cape");
		periodicInspection.setSiteId(1);
		
		periodicInspectionComment = new PeriodicInspectionComment();
		periodicInspectionComment.setViewerDate(LocalDateTime.now());
		
		ArrayList<PeriodicInspectionComment> listOfComments = new ArrayList<PeriodicInspectionComment>();
	    listOfComments.add(periodicInspectionComment);
	    periodicInspection.setPeriodicInspectorComment(listOfComments);
		
	}

	@Test
	public void testAddInspectionDetails_Success_Flow() throws InspectionException {
		Optional<PeriodicInspection> ipaolist;
		ipaolist = Optional.of(periodicInspection);

		inspectionServiceImpl.addInspectionDetails(periodicInspection);
		when(inspectionRepository.findBySiteId(periodicInspection.getSiteId())).thenReturn(ipaolist);
		InspectionException assertThrows = Assertions.assertThrows(InspectionException.class,
				() -> inspectionServiceImpl.addInspectionDetails(periodicInspection));
		equals(assertThrows.getMessage());

	}

	@Test
	public void testAddInspectionDetails_Invalid_Inputs() throws InspectionException {
		periodicInspection.setUserName(null);
		inspectionRepository.save(periodicInspection);

		InspectionException assertThrows = Assertions.assertThrows(InspectionException.class,
				() -> inspectionServiceImpl.addInspectionDetails(periodicInspection));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testAddInspectionDetails_Site_Id_Already_Present() throws InspectionException {

		Optional<PeriodicInspection> ipaolist;
		ipaolist = Optional.of(periodicInspection);

		when(inspectionRepository.findBySiteId(periodicInspection.getSiteId())).thenReturn(ipaolist);
		inspectionRepository.save(periodicInspection);
		InspectionException assertThrows = Assertions.assertThrows(InspectionException.class,
				() -> inspectionServiceImpl.addInspectionDetails(periodicInspection));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testRetrieveInspectionDetails_Success_Flow() throws InspectionException {

		List<PeriodicInspection> ipaolist = new ArrayList<>();
		ipaolist.add(periodicInspection);

		when(inspectionRepository.findByUserNameAndSiteId(periodicInspection.getUserName(), periodicInspection.getSiteId()))
				.thenReturn(ipaolist);
		inspectionServiceImpl.retrieveInspectionDetails("cape", 1);
		InspectionException assertThrows = Assertions.assertThrows(InspectionException.class,
				() -> inspectionServiceImpl.retrieveInspectionDetails(null, 1));
		equals(assertThrows.getMessage());
	}
	
	@Test
	public void testUpdateInspectionDetails() throws InspectionException {
		periodicInspection.setUserName("LVsystem@gmail.com");
		periodicInspection.setPeriodicInspectionId(1);
		when(inspectionRepository.findById(1)).thenReturn(Optional.of(periodicInspection));
		inspectionServiceImpl.updateInspectionDetails(periodicInspection);

		PeriodicInspection periodicInspection_1 = new PeriodicInspection();
		periodicInspection_1.setSiteId(12);
		periodicInspection_1.setUserName("cape");
		periodicInspection_1.setPeriodicInspectionId(12);

		when(inspectionRepository.findById(4)).thenReturn(Optional.of(periodicInspection));
		InspectionException assertThrows = Assertions.assertThrows(InspectionException.class,
				() -> inspectionServiceImpl.updateInspectionDetails(periodicInspection_1));
		assertEquals(assertThrows.getMessage(), "Given SiteId and ReportId is Invalid");

		periodicInspection.setSiteId(null);
		when(inspectionRepository.findById(2)).thenReturn(Optional.of(periodicInspection));
		InspectionException assertThrows_1 = Assertions.assertThrows(InspectionException.class,
				() -> inspectionServiceImpl.updateInspectionDetails(periodicInspection));
		assertEquals(assertThrows_1.getMessage(), "Invalid inputs");
	}
}