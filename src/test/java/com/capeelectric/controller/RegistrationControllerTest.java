package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.capeelectric.exception.RegistrationException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Register;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.service.impl.AWSEmailService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(PeriodicTestingControllerTest.class);

	@InjectMocks
	private RegistrationController registrationController;

	@MockBean
	private RegistrationService registrationService;

	@MockBean
	private RegistrationException registrationException;
	
	@MockBean
	private AWSEmailService awsEmailService;
	
	private Register register;

	{
		register = new Register();
		register.setUsername("lvsystem@capeindia.net");
		register.setPassword("moorthy");
		
	}

	@Test
	public void testAddRegistration() throws UserException, URISyntaxException, IOException, MessagingException, RegistrationException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(registrationService.addRegistration(register)).thenReturn(register);

		doNothing().when(awsEmailService).sendEmail(register.getUsername(),
				"You have been successfully Registered with Rush for Safety App. You may need to wait for 2hrs for getting approved from Admin.");
		 
		ResponseEntity<Void> addRegistration = registrationController.addRegistration(register);

		assertEquals(addRegistration.getStatusCode(), HttpStatus.CREATED);

	}
	
	@Test
	public void testRetrieveRegistration() throws RegistrationException {
		 
		when(registrationService.retrieveRegistration("lvsystem@capeindia.net")).thenReturn(Optional.of(register));

		 Optional<Register> retrieveRegistration = registrationController.retrieveRegistration(register.getUsername());

		assertEquals(retrieveRegistration.get().getUsername(),"lvsystem@capeindia.net");

	}
	

	@Test
	public void testUpdateRegistration()
			throws UserException, URISyntaxException, IOException, MessagingException, RegistrationException {
		doNothing().when(registrationService).updateRegistration(register);
		doNothing().when(awsEmailService).sendEmail(register.getUsername(),
				"You have successfully updated your profile");
		ResponseEntity<String> updateRegistration = registrationController.updateRegistration(register);
		assertEquals(updateRegistration.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	public void testRetrieveAllRegistration() throws RegistrationException {
		List<Register> listOfRegister = new ArrayList<Register>();
		when(registrationService.retrieveAllRegistration()).thenReturn(listOfRegister);

		List<Register> allRegistration = registrationController.retrieveAllRegistration();
		assertNotNull(allRegistration);
	}
	
}
