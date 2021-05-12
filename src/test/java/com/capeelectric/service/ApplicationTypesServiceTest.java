package com.capeelectric.service;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.model.ApplicationTypes;
import com.capeelectric.repository.ApplicationTypesRepository;
import com.capeelectric.service.impl.ApplicationTypesServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class ApplicationTypesServiceTest {

	@MockBean
	private ApplicationTypesRepository applicationTypesRepository;
	
	@MockBean
	private ApplicationTypesService applicationTypesService ;

	@InjectMocks
	private ApplicationTypesServiceImpl applicationTypesServiceImpl;

	private ApplicationTypes applicationTypes;

	{
		applicationTypes = new ApplicationTypes();
		applicationTypes.setId(1);
		applicationTypes.setType("Verification Of LV Systems ");
	}

	@Test
	public void testaddApplicationTypes()  {
		when(applicationTypesServiceImpl.addApplicationTypes(applicationTypes)).thenReturn(applicationTypes);
		applicationTypesRepository.save(applicationTypes);
	}

	@Test
	public void testupdateApplicationTypes() {
		Optional<ApplicationTypes> applicationtypes;
		applicationtypes = Optional.of(applicationTypes);
		when(applicationTypesRepository.findById(applicationTypes.getId())).thenReturn(applicationtypes);
		applicationTypesRepository.save(applicationTypes);
	}

	@Test
	public void testupdateApplicationTypes_ID_Not_Present()  {
		when(applicationTypesRepository.findById(applicationTypes.getId())).thenReturn(null);
		applicationTypesRepository.save(applicationTypes);
	}

	@Test
	public void retrieveTypes()  {
		List<ApplicationTypes> ApplicationList = new ArrayList<>();
		when(applicationTypesRepository.findAll()).thenReturn(ApplicationList);
		applicationTypesRepository.save(applicationTypes);
	}

	@Test
	public void deleteApplicationType() throws NullPointerException {
	   applicationTypesRepository.deleteById(applicationTypes.getId());
		applicationTypesRepository.delete(applicationTypes);
	}

}
