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
		user.setUsername("moorthy@capeindia.net");
		user.setPassword("moorthy");
		user.setEmail("moorthy@capeindia.net");
		user.setUserexist(true);
		user.setActive(true);

		optionaluser = Optional.of(user);
	}

	@Test
	public void testFindByUserName() throws UserException {

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		ResponseEntity<String> findByUserName = userDetailsServiceImpl.findByUserName("moorthy@capeindia.net");
		assertEquals(200, findByUserName.getStatusCodeValue());

		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.findByUserName(null));
		assertEquals("Email is required", assertThrows.getMessage());

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(null);
		UserException assertThrows2 = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.findByUserName("moorthy@capeindia.net"));
		assertEquals("Email is not available with us", assertThrows2.getMessage());

	}

	@Test
	public void testChangePassword() throws ChangePasswordException {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		String encodePass = encode.encode("moorthy");
		optionaluser.get().setPassword(encodePass);

		when(passwordEncoder.matches("moorthy", encodePass)).thenReturn(true);

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		User changePassword = userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy123");
		assertNull(changePassword);

		ChangePasswordException assertThrows1 = Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy1", "moorthy123"));
		assertEquals("Old password is not matching with encoded password", assertThrows1.getMessage());

		optionaluser.get().setPassword(encodePass);
		ChangePasswordException assertThrows2 = Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy"));
		assertEquals("Old password cannot be entered as new password", assertThrows2.getMessage());

		user.setUserexist(false);
		User changePassword2 = userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy123");
		assertNull(changePassword2);
	}

	@Test
	public void testUpdatedPassword() throws UserException {

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);

		User updatePassword = userDetailsServiceImpl.updatePassword("moorthy@capeindia.net", "moorthy123");
		assertNull(updatePassword);

		UsernameNotFoundException assertThrows = Assertions.assertThrows(UsernameNotFoundException.class,
				() -> userDetailsServiceImpl.updatePassword(null, "moorthy123"));
		assertEquals("username not valid", assertThrows.getMessage());

		user.setUserexist(false);
		UserException assertThrows2 = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.updatePassword("moorthy@capeindia.net", "moorthy123"));
		assertEquals("User Not available", assertThrows2.getMessage());

	}

	@Test
	public void testSaveUser() throws UserException {

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.saveUser(user));
		assertEquals("User already available", assertThrows.getMessage());
		user.setUserexist(false);
		User saveUser = userDetailsServiceImpl.saveUser(user);
		assertNull(saveUser);

	}

	@Test
	public void testRetrieveUserInformation() throws UserException {
		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		User retrieveUserInformation = userDetailsServiceImpl.retrieveUserInformation(user.getEmail());
		assertEquals("moorthy@capeindia.net", retrieveUserInformation.getEmail());

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
