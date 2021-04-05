package com.capeelectric.service;

import static org.mockito.Mockito.when;

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
import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest   {
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
		user.setUsername("thiru");
		user.setEmail("moorthy@capeindia.net");
		user.setUserexist(true);
		user.setActive(true);

		optionaluser = Optional.of(user);
	}

	@Test
	public void testFindByUserName() throws UserException {

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		userDetailsServiceImpl.findByUserName("moorthy@capeindia.net");

		Assertions.assertThrows(UserException.class, () -> userDetailsServiceImpl.findByUserName(null));

		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(null);
		Assertions.assertThrows(UserException.class,
				() -> userDetailsServiceImpl.findByUserName("moorthy@capeindia.net"));

	}

	@Test
	public void testChangePassword() throws ChangePasswordException {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
	    
		String encodePass = encode.encode("moorthy");
		optionaluser.get().setPassword(encodePass);
		
		when(passwordEncoder.matches("moorthy", encodePass)).thenReturn(true);
 		
		when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);
		userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy123");
			 
		Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy1", "moorthy123"));
		
		optionaluser.get().setPassword(encodePass);
		Assertions.assertThrows(ChangePasswordException.class,
				() -> userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy"));

		user.setUserexist(false);
 		Assertions.assertThrows(null ,
				() -> userDetailsServiceImpl.changePassword("moorthy@capeindia.net", "moorthy", "moorthy123"));
				 
	}

	
	  @Test 
	  public void testUpdatedPassword() throws UserException,Throwable{
 
			when(userRepository.findByUsername("moorthy@capeindia.net")).thenReturn(optionaluser);

			userDetailsServiceImpl.updatePassword("moorthy@capeindia.net", "moorthy123");

			Assertions.assertThrows(UsernameNotFoundException.class,
					() -> userDetailsServiceImpl.updatePassword(null, "moorthy123"));

			user.setUserexist(false);
			Assertions.assertThrows(UserException.class,
					() -> userDetailsServiceImpl.updatePassword("moorthy@capeindia.net", "moorthy123"));

	  }
	 
}
