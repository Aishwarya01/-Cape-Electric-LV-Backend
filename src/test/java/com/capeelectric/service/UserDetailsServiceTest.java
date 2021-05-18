package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {

	@MockBean
	private UserRepository userRepository;

	@InjectMocks
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@MockBean
	private BCryptPasswordEncoder passwordEncoder;

	private User user;

	private Optional<User> optionaluser;

	{
		user = new User();
		user.setUsername("lvsystem@capeindia.net");
		user.setPassword("cape");
		user.setEmail("lvsystem@capeindia.net");
		user.setUserexist(true);
		user.setActive(true);

		optionaluser = Optional.of(user);
	}

	@Test
	public void testFindByUserName() throws ForgotPasswordException {

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		ResponseEntity<String> findByUserName = userDetailsServiceImpl.findByUserName("lvsystem@capeindia.net");
		assertEquals(200, findByUserName.getStatusCodeValue());

		ForgotPasswordException assertThrows = Assertions.assertThrows(ForgotPasswordException.class,
				() -> userDetailsServiceImpl.findByUserName(null));
		assertEquals("Email is required", assertThrows.getMessage());

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(null);
		ForgotPasswordException assertThrows2 = Assertions.assertThrows(ForgotPasswordException.class,
				() -> userDetailsServiceImpl.findByUserName("lvsystem@capeindia.net"));
		assertEquals("Email is not available with us", assertThrows2.getMessage());

	} 

	@Test
	public void testChangePassword() throws ChangePasswordException {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		String encodePass = encode.encode("cape");
		optionaluser.get().setPassword(encodePass);

		when(passwordEncoder.matches("cape", encodePass)).thenReturn(true);

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		User changePassword = userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword);

		ChangePasswordException assertThrows1 = Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape1", "cape123"));
		assertEquals("Old password is not matching with encoded password", assertThrows1.getMessage());

		optionaluser.get().setPassword(encodePass);
		ChangePasswordException assertThrows2 = Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape"));
		assertEquals("Old password cannot be entered as new password", assertThrows2.getMessage());

		user.setUserexist(false);
		User changePassword2 = userDetailsServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword2);
	}

	@Test
	public void testUpdatedPassword() throws UpdatePasswordException {

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);

		User updatePassword = userDetailsServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123");
		assertNull(updatePassword);

		UsernameNotFoundException assertThrows = Assertions.assertThrows(UsernameNotFoundException.class,
				() -> userDetailsServiceImpl.updatePassword(null, "cape123"));
		assertEquals("username not valid", assertThrows.getMessage());

		user.setUserexist(false);
		  UpdatePasswordException assertThrows2 = Assertions.assertThrows(UpdatePasswordException.class,
				() -> userDetailsServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123"));
		assertEquals("User Not available", assertThrows2.getMessage());

	}

	@Test
	public void testSaveUser() throws UserException {

		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.saveUser(user));
		assertEquals("User already available", assertThrows.getMessage());
		user.setUserexist(false);
		User saveUser = userDetailsServiceImpl.saveUser(user);
		assertNull(saveUser);

	}

	@Test
	public void testRetrieveUserInformation() throws UserException {
		when(userRepository.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaluser);
		User retrieveUserInformation = userDetailsServiceImpl.retrieveUserInformation(user.getEmail());
		assertEquals("lvsystem@capeindia.net", retrieveUserInformation.getEmail());

		user.setUserexist(false);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.retrieveUserInformation(user.getEmail()));
		assertEquals("User not available", assertThrows.getMessage());

	}

	@Test
	public void testUpdateUserProfile() {
		User updateUserProfile = userDetailsServiceImpl.updateUserProfile(user);
		assertNull(updateUserProfile);
	}

}
