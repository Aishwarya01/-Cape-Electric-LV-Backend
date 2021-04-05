package com.capeelectric.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest   {
	@MockBean
	UserRepository userRepository;
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;

	@MockBean
	BCryptPasswordEncoder passwordEncoder;

	@Test
	void tsetFindByUserName() throws UserException {

 
		User user = new User();
		user.setUsername("sd@capeindia.net");

		Optional<User> optionaluser = Optional.of(user);

		when(userRepository.findByUsername("sd@capeindia.net")).thenReturn(optionaluser);
		 userDetailsServiceImpl.findByUserName("sd@capeindia.net");

 
	}

	@Test
 	void testchangePassword() throws ChangePasswordException {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
	    
		String encodePass = encode.encode("moorthy");

		boolean matches = true;
	 

		User user = new User();
		user.setPassword(encodePass);
		user.setUsername("thiru");
		user.setEmail("moorthy@capeindia.net");
		user.setUserexist(true);
		user.setActive(true);
		when(passwordEncoder.matches("moorthy", encodePass)).thenReturn(matches);
		
		Optional<User> optionaluser = Optional.of(user);

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy123");

		 
	}

	
	  @Test 
	  void testUpdatedPassword() throws UserException {
 
			User user = new User();
 			user.setEmail("moorthy@capeindia.net");
			user.setUserexist(true);
			user.setActive(true);
			Optional<User> optionaluser = Optional.of(user);

			  when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
			  
			  when(userDetailsServiceImpl.updatePassword("moorthy@capeindia.net","moorthy123")).thenReturn(user); }
	  
	 
}
