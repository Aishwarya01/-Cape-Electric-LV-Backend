package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.request.ChangePasswordRequest;
import com.capeelectric.service.AdminControllService;
import com.capeelectric.service.impl.AdminControllerServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
	
	@InjectMocks
	private AdminController adminController;
	
	@MockBean
	private AdminControllService adminControllService;
	
	@MockBean
	private AdminControllerServiceImpl adminControllerServiceImpl;
	
	private Admin admin;

	{
		admin = new Admin();
		admin.setUsername("abc@capeindia.net");
		admin.setPassword("123456789");
		admin.setEmail("abc@capeindia.net");
		admin.setUsertype("Admin");
		admin.setAdminexist(true);
	    admin.setAdminId(1);
	   
	}
	@Test
	public void testsaveAdmin() throws UserException, URISyntaxException{
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(adminControllerServiceImpl.saveAdmin(admin)).thenReturn(admin);
		ResponseEntity<Void> addAdmin = adminController.addAdmin(admin);
		assertEquals(addAdmin.getStatusCode(), HttpStatus.CREATED);
	
}
	@Test
	public void testretrieveAdminInformation() throws UserException{

		when(adminControllService.retrieveAdminInformation("abc@capeindia.net")).thenReturn(admin);
		Admin adminInfo = adminController.retrieveAdminInformation("abc@capeindia.net");
		assertEquals(adminInfo.isAdminexist(), true);

	}
	@Test
	public void testupdateAdminDetails() throws UserException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		//when(adminControllService.updateAdminDetails(admin)).thenReturn(admin);
		ResponseEntity<String> actualResponseEntity = adminController.updateAdminDetails(admin);
		//assertEquals(updateAdminDetails.getBody(), "abc@capeindia.net");
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}

	@Test
	public void testdeleteAdmin() throws UserException{
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = adminController.deleteByAdminId(1);
		assertEquals(actualResponseEntity, expectedResponseEntity);

	}
	@Test
	public void testForgotPassword() throws ForgotPasswordException, IOException, MessagingException {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(adminControllService.findByAdmin("abc@capeindia.net")).thenReturn(admin);
		ResponseEntity<String> forgotPassword = adminController.forgotPassword("abc@capeindia.net");
		assertEquals(forgotPassword.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void testUpdatePassword() throws UpdatePasswordException, IOException, MessagingException {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setEmail("abc@capeindia.net");
		authenticationRequest.setPassword("abcd12345");

		when(adminControllService.updatePassword("abc@capeindia.net", "abcd12345")).thenReturn(admin);
		ResponseEntity<String> updatePassword = adminController.updatePassword(authenticationRequest);
		assertEquals(updatePassword.getBody(), "abc@capeindia.net");
	}
	@Test
	public void testChangePassword() throws ChangePasswordException, IOException, MessagingException {
		ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
		changePasswordRequest.setOldPassword("abcd12345");
		changePasswordRequest.setEmail("abc@capeindia.net");
		changePasswordRequest.setPassword("abcd");

		when(adminControllService.changePassword("abc@capeindia.net", "abcd12345", "abcd")).thenReturn(admin);

		ResponseEntity<String> changePassword = adminController.changePassword(changePasswordRequest);
		assertEquals(changePassword.getBody(), "abc@capeindia.net");

	}

}
