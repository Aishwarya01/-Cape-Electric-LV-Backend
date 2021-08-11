package com.capeelectric.service;

import static org.mockito.Mockito.when;

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
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.service.impl.InspectionServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InspectionServiceImplTest {

	@InjectMocks
	private InspectionServiceImpl inspectionServiceImpl;

	@MockBean
	private InspectionRepository inspectionRepository;

	@MockBean
	private InspectionException inspectionException;

	private PeriodicInspection periodicInspection;

	{
		periodicInspection = new PeriodicInspection();
		periodicInspection.setUserName("cape");
		periodicInspection.setSiteId(1);
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
}