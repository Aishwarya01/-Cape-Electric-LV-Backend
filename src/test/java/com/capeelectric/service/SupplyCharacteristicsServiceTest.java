package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.service.impl.SupplyCharacteristicsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SupplyCharacteristicsServiceTest {

	@InjectMocks
	private SupplyCharacteristicsServiceImpl supplyCharacteristicsServiceImpl;

	@MockBean
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	@MockBean
	private SupplyCharacteristicsException supplyCharacteristicsException;

	private SupplyParameters supplyParameters;

	private SupplyCharacteristics supplyCharacteristics;

	{
		supplyCharacteristics = new SupplyCharacteristics();
		supplyCharacteristics.setSupplyCharacteristicsId(1);
		supplyCharacteristics.setUserName("cape");
		supplyCharacteristics.setSiteId(1);
		supplyCharacteristics.setMainNominalCurrent("1.2012,na,455,566");
		supplyCharacteristics.setMainNominalFrequency("NA,122.12,455,566");
		supplyCharacteristics.setMainNominalVoltage("3.00,152.1212,455.051,56.9459");
		supplyCharacteristics.setMainLoopImpedance("4.000,12.12245,455.21265,56.766456");
		supplyCharacteristics.setLiveConductorAC("3");
		
	}

	@Test
	public void testAddCharacteristics_Succes_Flow() throws SupplyCharacteristicsException {
		
		supplyCharacteristicsServiceImpl.addCharacteristics(supplyCharacteristics);
		when(supplyCharacteristicsRepository.save(supplyCharacteristics)).thenReturn(supplyCharacteristics);
		assertNotNull(supplyCharacteristics);
	}

	@Test
	public void testAddCharacteristics_Supply_Parameters_Succes_Flow() throws SupplyCharacteristicsException {

		supplyCharacteristics.setMainNominalCurrent("1.2012,12.1212,455,566");
		supplyCharacteristics.setMainNominalFrequency("2.00,122.12,455,566");
		supplyCharacteristics.setMainNominalVoltage("3.00,152.1212,455.051,56.9459");
		supplyCharacteristics.setMainLoopImpedance("4.000,12.12245,455.21265,56.766456");
		supplyParameters = new SupplyParameters();
		supplyParameters.setNominalFrequency("1.2450,12.412,44555,5456");
		supplyParameters.setNominalVoltage("2.050,12.1452,45545,5546");
		supplyParameters.setFaultCurrent("3.005,12.1542,44555.01,56.99");
		supplyParameters.setLoopImpedance("4.000,12.12254,455.21245,56.766");
		supplyParameters.setaLLiveConductorAC("3");

		List<SupplyParameters> list = new ArrayList<>();
		list.add(supplyParameters);
		supplyCharacteristics.setSupplyParameters(list);

		when(supplyCharacteristicsRepository.save(supplyCharacteristics)).thenReturn(supplyCharacteristics);
		supplyCharacteristicsServiceImpl.addCharacteristics(supplyCharacteristics);
		assertNotNull(supplyParameters);
	}

	@Test
	public void testAddCharacteristics_Invalid_Inputs() throws SupplyCharacteristicsException {
		supplyCharacteristics = null;

		when(supplyCharacteristicsRepository.save(supplyCharacteristics)).thenReturn(supplyCharacteristics);
		SupplyCharacteristicsException assertThrows = Assertions.assertThrows(SupplyCharacteristicsException.class,
				() -> supplyCharacteristicsServiceImpl.addCharacteristics(supplyCharacteristics));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testAddCharacteristics_Site_Alredy_Present() throws SupplyCharacteristicsException {

		Optional<SupplyCharacteristics> supplylist;
		supplylist = Optional.of(supplyCharacteristics);

		when(supplyCharacteristicsRepository.findBySiteId(supplyCharacteristics.getSiteId())).thenReturn(supplylist);
		SupplyCharacteristicsException assertThrows = Assertions.assertThrows(SupplyCharacteristicsException.class,
				() -> supplyCharacteristicsServiceImpl.addCharacteristics(supplyCharacteristics));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testRetrieveCharacteristics_Succes_Flow() throws SupplyCharacteristicsException {

		List<SupplyCharacteristics> supplylist = new ArrayList<>();
		supplylist.add(supplyCharacteristics);

		when(supplyCharacteristicsRepository.findByUserNameAndSiteId(supplyCharacteristics.getUserName(),
				supplyCharacteristics.getSiteId())).thenReturn(supplylist);
		List<SupplyCharacteristics> installationReport = supplyCharacteristicsServiceImpl
				.retrieveCharacteristics("cape", 1);
		assertNotNull(supplyCharacteristics);
		equals(installationReport);
	}

	@Test
	public void testRetrieveCharacteristics_Invalid_Inputs() throws SupplyCharacteristicsException {

		List<SupplyCharacteristics> supplylist = new ArrayList<>();
		supplyCharacteristics.setUserName(null);
		supplylist.add(supplyCharacteristics);

		when(supplyCharacteristicsRepository.findByUserNameAndSiteId(supplyCharacteristics.getUserName(),
				supplyCharacteristics.getSiteId())).thenReturn(supplylist);
		SupplyCharacteristicsException assertThrows = Assertions.assertThrows(SupplyCharacteristicsException.class,
				() -> supplyCharacteristicsServiceImpl.retrieveCharacteristics(null, 1));
		equals(assertThrows.getMessage());
	}
	
	@Test
	public void testAddCharacteristics_With_NA_Value() throws SupplyCharacteristicsException {
		supplyCharacteristics.setMainNominalCurrent("1.2012,na,455,566");
		supplyCharacteristics.setMainNominalFrequency("NA,122.12,455,566");
		
		when(supplyCharacteristicsRepository.save(supplyCharacteristics)).thenReturn(supplyCharacteristics);
		supplyCharacteristicsServiceImpl.addCharacteristics(supplyCharacteristics);
	}
}