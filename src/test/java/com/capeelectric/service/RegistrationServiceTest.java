/*
 * package com.capeelectric.service;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull; import static
 * org.mockito.Mockito.when;
 * 
 * import java.util.ArrayList; import java.util.List; import java.util.Optional;
 * 
 * import org.junit.jupiter.api.Assertions; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import
 * org.mockito.InjectMocks; import org.mockito.junit.jupiter.MockitoExtension;
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.test.context.junit.jupiter.SpringExtension;
 * 
 * import com.capeelectric.exception.RegisterPermissionRequestException; import
 * com.capeelectric.exception.RegistrationException; import
 * com.capeelectric.model.Register; import
 * com.capeelectric.repository.RegistrationRepository; import
 * com.capeelectric.request.RegisterPermissionRequest; import
 * com.capeelectric.service.impl.RegistrationServiceImpl;
 * 
 * @ExtendWith(SpringExtension.class)
 * 
 * @ExtendWith(MockitoExtension.class) public class RegistrationServiceTest {
 * 
 * private static final Logger logger =
 * LoggerFactory.getLogger(RegistrationServiceTest.class);
 * 
 * @MockBean private RegistrationRepository registrationRepository;
 * 
 * @InjectMocks private RegistrationServiceImpl registrationServiceImpl;
 * 
 * private Register register;
 * 
 * { register = new Register(); register.setRegisterId(1);
 * register.setAddress("chennai"); register.setPassword("cape123");
 * register.setApplicationType("HV,LV,EMC"); register.setCompanyName("CAPE");
 * register.setContactNumber("9023092802"); register.setCountry("INDIA");
 * register.setDepartment("ECE"); register.setDesignation("INSPECTOR");
 * register.setInterestedAreas("Learning"); register.setName("Cape");
 * register.setUsername("lvsystem@capeindia.net"); register.setState("TN"); }
 * 
 * @Test public void testAddRegistration() throws RegistrationException {
 * logger.info("RegistrationServiceTest testAddRegistration_funcion Started");
 * 
 * Optional<Register> optionalRegister = Optional.of(register);
 * 
 * when(registrationRepository.findByUsername("lvsystem@capeindia.net")).
 * thenReturn(optionalRegister);
 * when(registrationRepository.save(register)).thenReturn(register);
 * 
 * //Success flow register.setUsername("lvsystem123@capeindia.net"); Register
 * addRegistration = registrationServiceImpl.addRegistration(register);
 * assertEquals(addRegistration.getDepartment(), "ECE");
 * 
 * //Exception --> Given UserName Already Present
 * register.setUsername("lvsystem@capeindia.net"); RegistrationException
 * assertThrows = Assertions.assertThrows(RegistrationException.class, () ->
 * registrationServiceImpl.addRegistration(register));
 * 
 * assertEquals("Given UserName Already Present", assertThrows.getMessage());
 * 
 * //Exception --> Invalid Inputs register.setUsername(null);
 * RegistrationException assertThrows_1 =
 * Assertions.assertThrows(RegistrationException.class, () ->
 * registrationServiceImpl.addRegistration(register));
 * 
 * assertEquals("Invalid Inputs", assertThrows_1.getMessage());
 * logger.info("RegistrationServiceTest testAddRegistration_funcion Started");
 * 
 * }
 * 
 * @Test public void testUpdateRegistration() throws RegistrationException {
 * logger.info("RegistrationServiceTest testUpdateRegistration_funcion Started"
 * );
 * 
 * Optional<Register> optionalRegister = Optional.of(register);
 * 
 * when(registrationRepository.findById(register.getRegisterId())).thenReturn(
 * optionalRegister);
 * when(registrationRepository.save(register)).thenReturn(register);
 * 
 * //Success flow registrationServiceImpl.updateRegistration(register);
 * 
 * //Throwing Exception RegistrationException assertThrows =
 * Assertions.assertThrows(RegistrationException.class, () ->
 * registrationServiceImpl.updateRegistration(register()));
 * 
 * assertEquals("Given User not present", assertThrows.getMessage());
 * 
 * //Throwing Exception register.setUsername(null); RegistrationException
 * assertThrows_1 = Assertions.assertThrows(RegistrationException.class, () ->
 * registrationServiceImpl.updateRegistration(register));
 * 
 * assertEquals("Invalid Inputs", assertThrows_1.getMessage());
 * 
 * logger.info("RegistrationServiceTest testUpdateRegistration_funcion Started"
 * ); }
 * 
 * @Test public void testRetrieveRegistration() throws RegistrationException {
 * logger.
 * info("RegistrationServiceTest testRetrieveRegistration_funcion Started");
 * 
 * Optional<Register> optionalRegister = Optional.of(register);
 * 
 * when(registrationRepository.findByUsername("lvsystem@capeindia.net")).
 * thenReturn(optionalRegister);
 * 
 * //Success flow Optional<Register> retrieveRegistration =
 * registrationServiceImpl .retrieveRegistration("lvsystem@capeindia.net");
 * assertNotNull(retrieveRegistration);
 * 
 * //Throwing Exception RegistrationException assertThrows =
 * Assertions.assertThrows(RegistrationException.class, () ->
 * registrationServiceImpl.retrieveRegistration(null));
 * 
 * assertEquals("Invalid Inputs", assertThrows.getMessage());
 * 
 * logger.
 * info("RegistrationServiceTest testRetrieveRegistration_funcion Started");
 * 
 * }
 * 
 * private Register register() { Register register2 = new Register();
 * register2.setRegisterId(2); register2.setAddress("chennai");
 * register2.setPassword("cape123"); register2.setApplicationType("HV,LV,EMC");
 * register2.setCompanyName("CAPE"); register2.setContactNumber("9023092802");
 * register2.setCountry("INDIA"); register2.setDepartment("ECE");
 * register2.setDesignation("INSPECTOR");
 * register2.setInterestedAreas("Learning"); register2.setName("Cape");
 * register2.setUsername("lvsystem@capeindia.net"); register2.setState("TN");
 * return register2; }
 * 
 * }
 */