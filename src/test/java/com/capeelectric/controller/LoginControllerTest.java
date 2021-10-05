package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.config.JwtTokenUtil;
import com.capeelectric.exception.AuthenticationException;
import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.Register;
import com.capeelectric.model.RegisterDetails;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.request.ChangePasswordRequest;
import com.capeelectric.service.impl.AWSEmailService;
import com.capeelectric.service.impl.LoginServiceImpl;
import com.capeelectric.service.impl.RegistrationDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

	@InjectMocks
	private LoginController loginController;

	@MockBean
	private LoginServiceImpl loginServiceImpl;

	@MockBean
	private RegistrationDetailsServiceImpl registrationDetailsServiceImpl;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private AWSEmailService emailService;

	@MockBean
	private RegistrationRepository registrationRepository;

	private Register register;

	{
		register = new Register();
		register.setUsername("lvsystem@capeindia.net");
		register.setPassword("cape");
		register.setRegisterId(1);
		register.setPermission("YES");
		
	}

	@Test
	public void testCreateAuthenticationToken() throws AuthenticationException, Exception {
		RegisterDetails registerDetails = new RegisterDetails();
		registerDetails.setUsername("lvsystem@capeindia.net");
		registerDetails.setPassword("abcd12345");

		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("lvsystem@capeindia.net");
		authenticationRequest.setPassword("abcd12345");

		when(registrationDetailsServiceImpl.loadUserByUsername("lvsystem@capeindia.net")).thenReturn(registerDetails);
		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(Optional.of(register));
		ResponseEntity<?> token = loginController.createAuthenticationToken(authenticationRequest);
		assertNotNull(token);

		register.setPermission("NO");
		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(Optional.of(register));
		AuthenticationException assertThrows = Assertions.assertThrows(AuthenticationException.class,
				() -> loginController.createAuthenticationToken(authenticationRequest));
		
		assertEquals(assertThrows.getMessage(), "Admin not approved for Your registration");
		
	}

	@Test
	public void testForgotPassword() throws ForgotPasswordException, IOException, MessagingException {

		when(loginServiceImpl.findByUserName("lvsystem@capeindia.net")).thenReturn(register);
		doNothing().when(emailService).sendEmail("lvsystem@capeindia.net",
				"You have initiated an change in password." + "\n" + "lvsystem@capeindia.net");
		ResponseEntity<String> forgotPassword = loginController.forgotPassword("lvsystem@capeindia.net");
		assertEquals(forgotPassword.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdatePassword() throws UpdatePasswordException, IOException, MessagingException {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("lvsystem@capeindia.net");
		authenticationRequest.setPassword("abcd12345");

		when(loginServiceImpl.updatePassword("lvsystem@capeindia.net", "abcd12345")).thenReturn(register);
		ResponseEntity<String> updatePassword = loginController.updatePassword(authenticationRequest);
		assertEquals(updatePassword.getBody(), "You have Successfully Updated Your Password");
	}

	@Test
	public void testChangePassword() throws ChangePasswordException, IOException, MessagingException {
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		changePasswordRequest.setOldPassword("abcd12345");
		changePasswordRequest.setEmail("lvsystem@capeindia.net");
		changePasswordRequest.setPassword("abcd");

		when(loginServiceImpl.changePassword("lvsystem@capeindia.net", "abcd12345", "abcd")).thenReturn(register);

		ResponseEntity<String> changePassword = loginController.changePassword(changePasswordRequest);
		assertEquals(changePassword.getBody(), "lvsystem@capeindia.net");

	}
	
	@Test
	public void testCreatePassword()
			throws  UpdatePasswordException, IOException, MessagingException {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("lvsystem@capeindia.net");
		authenticationRequest.setPassword("abcd");

		when(loginServiceImpl.createPassword(authenticationRequest)).thenReturn(register);

		ResponseEntity<String> createPassword = loginController.createPassword(authenticationRequest);
		assertEquals(createPassword.getBody(), "You have Successfully Created Your Password");

	}
	
	@Test
	public void testRetrieveInformation()
			throws RegistrationException, ForgotPasswordException, IOException, MessagingException {
		ResponseEntity<Register> retrieveInformation = loginController.retrieveInformation("lvsystem@capeindia.net");
		assertEquals(retrieveInformation.getStatusCode(), HttpStatus.OK);
	}
}
