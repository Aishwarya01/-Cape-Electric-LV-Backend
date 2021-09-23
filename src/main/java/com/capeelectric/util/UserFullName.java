package com.capeelectric.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.Register;
import com.capeelectric.model.User;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.repository.UserRepository;


/**
 * @author capeelectricsoftware
 *
 */
@Configuration
public class UserFullName {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegistrationRepository registrationRepository;

	/**
	 * Method to return Full Name based on UserName
	 * 
	 * @param userName
	 * @return
	 */
	public String getFullName(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent() && user.get() != null)
			return user.get().getFirstname() + " " + user.get().getLastname();
		return "";
	}

	public String findByUserName(String userName) {
		Optional<Register> registerRepo = registrationRepository.findByUsername(userName);

		if (registerRepo.isPresent() && registerRepo.get() != null && registerRepo.get().getName() != null) {
			return registerRepo.get().getName();
		}
		return null;

	}
}
