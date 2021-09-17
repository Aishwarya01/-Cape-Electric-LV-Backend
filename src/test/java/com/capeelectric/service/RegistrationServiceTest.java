package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.request.RegisterPermissionRequest;
import com.capeelectric.service.impl.RegistrationServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceTest.class);

	@MockBean
	private RegistrationRepository registrationRepository;

	@InjectMocks
	private RegistrationServiceImpl registrationServiceImpl;

	private Register register;

	{
		register = new Register();
		register.setRegisterId(1);
		register.setAddress("chennai");
		register.setPassword("cape123");
		register.setApplicationType("HV,LV,EMC");
		register.setCompanyName("CAPE");
		register.setContactNumber("9023092802");
		register.setCountry("INDIA");
		register.setDepartment("ECE");
		register.setDesignation("INSPECTOR");
		register.setInterestedAreas("Learning");
		register.setName("Cape");
		register.setUsername("lvsystem@capeindia.net");
		register.setState("TN");
	}

	@Test
	public void testRetrieveAllRegistration() throws RegistrationException {
		logger.info("RegistrationServiceTest testRetrieveAllRegistration_funcion Started");

		List<Register> listOfRegistration = new ArrayList<Register>();
		listOfRegistration.add(register);
		listOfRegistration.add(register());

		when(registrationRepository.findAll()).thenReturn(listOfRegistration);

		//Success flow
		List<Register> retrieveAllRegistration = registrationServiceImpl.retrieveAllRegistration();
		assertNotNull(retrieveAllRegistration);

		logger.info("RegistrationServiceTest testRetrieveAllRegistration_funcion Started");
	}
	
	@Test
	public void testUpdatePermission() throws RegistrationException, RegisterPermissionRequestException {
		logger.info("RegistrationServiceTest testUpdatePermission_funcion Started");

		Optional<Register> optionalRegister = Optional.of(register);
		
		when(registrationRepository.findById(permissionRequest("YES").getRegisterId())).thenReturn(optionalRegister);
		when(registrationRepository.save(register)).thenReturn(register);

		//Success flow
		Register updatePermission_1 = registrationServiceImpl.updatePermission(permissionRequest("YES"));
		assertEquals(updatePermission_1.getPermission(), permissionRequest("YES").getPermission());
		
		//Success flow
		Register updatePermission_2 = registrationServiceImpl.updatePermission(permissionRequest("NO"));
		assertEquals(updatePermission_2.getPermission(), permissionRequest("NO").getPermission());

		//Throwing Exception
		RegisterPermissionRequest permissionRequest = permissionRequest("YES");
		permissionRequest.setRegisterId(2);
		  RegisterPermissionRequestException assertThrows = Assertions.assertThrows(RegisterPermissionRequestException.class,
				() -> registrationServiceImpl.updatePermission(permissionRequest));

		assertEquals("Given User not avilable", assertThrows.getMessage());

		//Throwing Exception
		RegisterPermissionRequestException assertThrows_1 = Assertions.assertThrows(RegisterPermissionRequestException.class,
				() -> registrationServiceImpl.updatePermission(null));

		assertEquals("RegisterPermissionRequest has Invaild Input", assertThrows_1.getMessage());
		
		logger.info("RegistrationServiceTest testUpdatePermission_funcion Started");

	}
	
	private RegisterPermissionRequest permissionRequest(String permission) {
		RegisterPermissionRequest permissionRequest = new RegisterPermissionRequest();
		permissionRequest.setAdminUserName("lvsystem@capeindia.net");
		permissionRequest.setComment("your company information not avilable");
		permissionRequest.setRegisterId(1);
		permissionRequest.setPermission(permission);
		return permissionRequest;
	}
	
	private Register register() {
		Register register2 = new Register();
		register2.setRegisterId(2);
		register2.setAddress("chennai");
		register2.setPassword("cape123");
		register2.setApplicationType("HV,LV,EMC");
		register2.setCompanyName("CAPE");
		register2.setContactNumber("9023092802");
		register2.setCountry("INDIA");
		register2.setDepartment("ECE");
		register2.setDesignation("INSPECTOR");
		register2.setInterestedAreas("Learning");
		register2.setName("Cape");
		register2.setUsername("lvsystem@capeindia.net");
		register2.setState("TN");
		return register2;
	}

}
