package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.request.AuthenticationRequest;
import com.capeelectric.service.impl.LoginServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

	@MockBean
	private RegistrationRepository registrationRepository;

	@InjectMocks
	private LoginServiceImpl loginServiceImpl;

	@MockBean
	private BCryptPasswordEncoder passwordEncoder;
	
	@MockBean
	private AuthenticationRequest authenticationRequest;
	
	@MockBean
	private UsernameNotFoundException usernameNotFoundException;

	private Register register;

	{
		register = new Register();
		register.setUsername("lvsystem@capeindia.net");
		register.setPassword("cape");

	}
	
	@Test
	public void testFindByUserName() throws ForgotPasswordException, IOException {

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(Optional.of(register));
		Register findByUserName = loginServiceImpl.findByUserName("lvsystem@capeindia.net");
		assertEquals("lvsystem@capeindia.net", findByUserName.getUsername());

		ForgotPasswordException assertThrows_1 = Assertions.assertThrows(ForgotPasswordException.class,
				() -> loginServiceImpl.findByUserName(null));
		assertEquals("Email is required", assertThrows_1.getMessage());

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(Optional.of(register));
		ForgotPasswordException assertThrows_2 = Assertions.assertThrows(ForgotPasswordException.class,
				() -> loginServiceImpl.findByUserName(""));
		assertEquals("Email is not available with us", assertThrows_2.getMessage());

	}

	@Test
	public void testChangePassword() throws ChangePasswordException {
		Optional<Register> optionalRegister = Optional.of(register);
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		String encodePass = encode.encode("cape");
		optionalRegister.get().setPassword(encodePass);

		when(passwordEncoder.matches("cape", encodePass)).thenReturn(true);

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionalRegister);
		  Register changePassword = loginServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword);

		ChangePasswordException assertThrows1 = Assertions.assertThrows(ChangePasswordException.class,
				() -> loginServiceImpl.changePassword("lvsystem@capeindia.net", "cape1", "cape123"));
		assertEquals("Old password is not matching with encoded password", assertThrows1.getMessage());

		optionalRegister.get().setPassword(encodePass);
		ChangePasswordException assertThrows2 = Assertions.assertThrows(ChangePasswordException.class,
				() -> loginServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape"));
		assertEquals("Old password cannot be entered as new password", assertThrows2.getMessage());

	}
	
	@Test
	public void testUpdatedPassword() throws UpdatePasswordException, UsernameNotFoundException {
		Optional<Register> optionalRegister = Optional.of(register);

		when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionalRegister);

		 Register updatePassword = loginServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123");		 
		 assertNull(updatePassword);

		 register.setUsername("lvsystem123@capeindia.net");
		 Optional<Register> optionalRegister_1 = Optional.of(register);
		 when(registrationRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionalRegister_1);
		   UpdatePasswordException assertThrows_1 = Assertions.assertThrows(UpdatePasswordException.class,
				() -> loginServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123"));
			assertEquals("User Not available", assertThrows_1.getMessage());

			UsernameNotFoundException assertThrows2 = Assertions.assertThrows(UsernameNotFoundException.class,
				() -> loginServiceImpl.updatePassword(null, "cape123"));
		assertEquals("Username not valid", assertThrows2.getMessage());

	}
	 
}
