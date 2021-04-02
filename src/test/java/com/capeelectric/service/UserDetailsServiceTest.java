package com.capeelectric.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDetailsServiceTest {

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	void tsetFindByUserName() throws UserException {

		UserRepository mock = Mockito.mock(UserRepository.class);
		UserDetailsServiceImpl mock1 = Mockito.mock(UserDetailsServiceImpl.class);

		User user2 = new User();
		user2.setUsername("sd@capeindia.net");

		Optional<User> user = Optional.of(user2);

		when(mock.findByUsername("sd@capeindia.net")).thenReturn(user);

	}

	@Test
	void testchangePassword() throws ChangePasswordException {

		UserDetailsServiceImpl mock1 = Mockito.mock(UserDetailsServiceImpl.class);

		User user = new User();
		user.setEmail("moorthy@capeindia.net");
		user.setPassword("moorthy");
		when(mock1.changePassword("moorthy@capeindia.net", "moorthy", "thiru123")).thenReturn(user);
	}

	@Test
	void testUpdatedPassword() throws UserException {
		UserDetailsServiceImpl mock1 = Mockito.mock(UserDetailsServiceImpl.class);

		User user = new User();
		user.setEmail("moorthy@capeindia.net");
		user.setPassword("moorthy");

		when(mock1.updatePassword("moorthy@capeindia.net", "moorthy")).thenReturn(user);
	}

}
