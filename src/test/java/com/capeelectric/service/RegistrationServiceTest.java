
package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.impl.RegistrationServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceTest.class);

	@MockBean
	private OtpConfig otpConfig;

	@MockBean
	private RegistrationRepository registrationRepository;

	@InjectMocks
	private RegistrationServiceImpl registrationServiceImpl;
	
	@Mock
	private RestTemplate restTemplate;

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
		register.setName("Cape");
		register.setUsername("lvsystem@capeindia.net");
		register.setState("TN");
		register.setPermission("yes");
	}

	@Test
	public void testAddRegistration() throws RegistrationException {
		logger.info("RegistrationServiceTest testAddRegistration_funcion Started");

		Optional<Register> optionalRegister = Optional.of(register);

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionalRegister);
		when(registrationRepository.save(register)).thenReturn(register);

		// Success flow
		register.setUsername("lvsystem123@capeindia.net");
		register.setRole("INSPECTOR");
		Register addRegistration = registrationServiceImpl.addRegistration(register);
		assertEquals(addRegistration.getUsername(), "lvsystem123@capeindia.net");

		// Exception --> Invalid MobileNumber
		register.setContactNumber("89988");
		RegistrationException invalidMobileNumber = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.addRegistration(register));

		assertEquals("Invalid MobileNumber", invalidMobileNumber.getMessage());

		// Exception --> Given UserName Already Present
		register.setUsername("lvsystem@capeindia.net");
		RegistrationException assertThrows = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.addRegistration(register));

		assertEquals("Given UserName Already Present", assertThrows.getMessage());

		// Exception --> Invalid Inputs
		register.setUsername(null);
		RegistrationException assertThrows_1 = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.addRegistration(register));

		assertEquals("Invalid Inputs", assertThrows_1.getMessage());
		logger.info("RegistrationServiceTest testAddRegistration_funcion Started");

	}
	
	@Test
	public void testAddViewerRegistration() throws RegistrationException {
		logger.info("RegistrationServiceTest testAddViewerRegistration_funcion Started");

		Optional<Register> optionalRegister = Optional.of(register);

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionalRegister);
		when(registrationRepository.save(register)).thenReturn(register);

		// Success flow
		register.setUsername("lvsystem123@capeindia.net");
		Register addRegistration = registrationServiceImpl.addViewerRegistration(register);
		assertEquals(addRegistration.getUsername(), "lvsystem123@capeindia.net");

		// Exception --> Invalid MobileNumber
		register.setContactNumber("89988");
		RegistrationException invalidMobileNumber = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.addViewerRegistration(register));

		assertEquals("Invalid MobileNumber", invalidMobileNumber.getMessage());

		// Exception --> Given UserName Already Present
		register.setUsername("lvsystem@capeindia.net");
		RegistrationException assertThrows = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.addViewerRegistration(register));

		assertEquals("Given UserName Already Present", assertThrows.getMessage());

		// Exception --> Invalid Inputs
		register.setUsername(null);
		RegistrationException assertThrows_1 = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.addViewerRegistration(register));

		assertEquals("Invalid Inputs", assertThrows_1.getMessage());
		logger.info("RegistrationServiceTest testAddViewerRegistration_funcion Started");

	}

	@Test
	public void testUpdateRegistration() throws RegistrationException {
		logger.info("RegistrationServiceTest testUpdateRegistration_funcion Started");

		Optional<Register> optionalRegister = Optional.of(register);

		when(registrationRepository.findById(register.getRegisterId())).thenReturn(optionalRegister);
		when(registrationRepository.save(register)).thenReturn(register);

		// Success flow 
		registrationServiceImpl.updateRegistration(register);

		// Throwing Exception 
		RegistrationException assertThrows = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.updateRegistration(register()));

		assertEquals("Given User not present", assertThrows.getMessage());

		// Throwing Exception
		register.setUsername(null); 
		RegistrationException assertThrows_1 = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.updateRegistration(register));

		assertEquals("Invalid Inputs", assertThrows_1.getMessage());

		logger.info("RegistrationServiceTest testUpdateRegistration_funcion Started");
	}

	@Test
	public void testRetrieveRegistration() throws RegistrationException {
		logger.info("RegistrationServiceTest testRetrieveRegistration_funcion Started");

		Optional<Register> optionalRegister = Optional.of(register);

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionalRegister);

		// Success flow
		Optional<Register> retrieveRegistration = registrationServiceImpl
				.retrieveRegistration("lvsystem@capeindia.net");
		assertNotNull(retrieveRegistration);

		// Email-Id doesn't exist
		RegistrationException exception = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.retrieveRegistration("lvsystem1@capeindia.net"));

		assertEquals("Email Id doesn't exist!", exception.getMessage());

		// Throwing Exception
		RegistrationException assertThrows = Assertions.assertThrows(RegistrationException.class,
				() -> registrationServiceImpl.retrieveRegistration(null));

		assertEquals("Invalid Inputs", assertThrows.getMessage());

		logger.info("RegistrationServiceTest testRetrieveRegistration_funcion End");

	}
	
	@Test
	public void testSendOtp() throws RegistrationException {
		logger.info("RegistrationServiceTest testSendOtp_funcion Started");

		when(restTemplate.exchange(otpConfig.getSendOtp() + "9023092802", HttpMethod.GET, null,
				String.class))
						.thenReturn(new ResponseEntity<String>(
								"{\"Status\":\"Success\",\"Details\":\"a2075b4a-25f8-44c1-824a-fd89cc310821\"}",
								HttpStatus.ACCEPTED));

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(Optional.of(register));
		
		// Success flow
		registrationServiceImpl.sendOtp("lvsystem@capeindia.net", "9023092802");
		
		// Throwing Exception --> Invalid MobileNumber
		RegistrationException assertThrows_1 = Assertions.assertThrows(RegistrationException.class, ()-> registrationServiceImpl.sendOtp("lvsystem@capeindia.net", "92802"));
		assertEquals(assertThrows_1.getMessage(), "Invalid MobileNumber");
		
		// Throwing Exception --> Enter registered MobileNumber
		RegistrationException assertThrows_2 = Assertions.assertThrows(RegistrationException.class, ()-> registrationServiceImpl.sendOtp("lvsystem@capeindia.net", "9053092802"));
		assertEquals(assertThrows_2.getMessage(), "Enter registered MobileNumber");
		
		// Throwing Exception --> Admin not approved for Your registration
		register.setPermission("NO");
		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(Optional.of(register));
		RegistrationException assertThrows_3 = Assertions.assertThrows(RegistrationException.class, ()-> registrationServiceImpl.sendOtp("lvsystem@capeindia.net", "9023092802"));
		assertEquals(assertThrows_3.getMessage(), "Admin not approved for Your registration");
		
		// Throwing Exception --> Invalid Input
		RegistrationException assertThrows_4 = Assertions.assertThrows(RegistrationException.class, ()-> registrationServiceImpl.sendOtp("lvsystem@capeindia.net", null));
		assertEquals(assertThrows_4.getMessage(), "Invalid Input");
		
		logger.info("RegistrationServiceTest testSendOtp_funcion End");

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
		register2.setName("Cape");
		register2.setUsername("lvsystem12@capeindia.net");
		register2.setState("TN");
		return register2;
	}

}
