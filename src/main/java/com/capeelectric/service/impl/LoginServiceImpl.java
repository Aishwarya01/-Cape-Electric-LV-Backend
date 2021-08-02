package com.capeelectric.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * Method to retrieve the user
	 * 
	 * @throws IOException
	 */
	@Override
	public Register findByUserName(String email) throws ForgotPasswordException, IOException {
		logger.debug("Find By User Name Starts");
		if (email != null) {
			Optional<Register> registerRepo = registrationRepository.findByUsername(email);
			if (registerRepo != null && registerRepo.isPresent() && registerRepo.get() != null) {
				logger.debug("Find By User Name Ends");
				return registerRepo.get();
			} else {
				logger.debug("Find By User Name Ends");
				throw new ForgotPasswordException("Email is not available with us");
			}
		} else {
			logger.debug("Find By User Name Ends");
			throw new ForgotPasswordException("Email is required");
		}
	}

	/**
	 * Method to update the user after changing the password
	 * 
	 * @throws UserException
	 */
	@Override
	public Register updatePassword(String email, String password) throws UpdatePasswordException {
		// TODO: Email triggering
		logger.debug("UpdatePassword Starts");
		if (email != null && password != null) {
			Register register = registrationRepository.findByUsername(email).get();
			if (register != null && register.getUsername().equalsIgnoreCase(email)) {
				register.setPassword(passwordEncoder.encode(password));
				register.setUpdatedDate(LocalDateTime.now());
				register.setUpdatedBy(email);
				logger.debug("UpdatePassword Ends");
				return registrationRepository.save(register);
			} else {
				logger.debug("UpdatePassword Ends");
				throw new UpdatePasswordException("User Not available");
			}
		} else {
			logger.debug("UpdatePassword Ends");
			throw new UsernameNotFoundException("username not valid");
		}
	}

	/**
	 * 
	 */
	@Override
	public Register changePassword(String email, String oldPassword, String password) throws ChangePasswordException {
		logger.debug("Change Password Starts");
		Register registerRepo = registrationRepository.findByUsername(email).get();
		if (!passwordEncoder.matches(oldPassword, registerRepo.getPassword())) {
			logger.debug("Change Password Ends");
			throw new ChangePasswordException("Old password is not matching with encoded password");
		} else if (oldPassword.equalsIgnoreCase(password)) {
			logger.debug("Change Password Ends");
			throw new ChangePasswordException("Old password cannot be entered as new password");
		} else {
			if (registerRepo != null && registerRepo.getUsername().equalsIgnoreCase(email)) {
				registerRepo.setPassword(passwordEncoder.encode(password));
				registerRepo.setUpdatedDate(LocalDateTime.now());
				registerRepo.setUpdatedBy(email);
				logger.debug("Update User Ends");
				return registrationRepository.save(registerRepo);
			}
		}
		return null;
	}

}
