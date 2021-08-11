package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.request.RegisterPermissionRequest;
import com.capeelectric.service.RegistrationService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private RegistrationRepository registerRepository;
	
	@Override
	public Register addRegistration(Register register) throws RegistrationException {
		logger.debug("AddingRegistration Starts with User : {} ", register.getUsername());
		if (register.getUsername() != null && register.getCompanyName() != null && register.getAddress() != null
				&& register.getApplicationType() != null && register.getContactNumber() != null
				&& register.getDepartment() != null && register.getDesignation() != null
				&& register.getInterestedAreas() != null && register.getName() != null && register.getState() != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(register.getUsername());
			if (!registerRepo.isPresent()
					|| !registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				register.setCreatedDate(LocalDateTime.now());
				register.setPermission("NOT_AUTHORIZED");
				register.setRole("Inspector");
				register.setUpdatedDate(LocalDateTime.now());
				register.setCreatedBy(register.getUsername());
				register.setUpdatedBy(register.getUsername());
				logger.debug("AddingRegistration Ended");
				return registerRepository.save(register);
			} else {
				logger.debug("Given UserName Already Present");
				throw new RegistrationException("Given UserName Already Present");
			}

		} else {
			logger.debug("AddingRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}
	}

	@Override
	public Optional<Register> retrieveRegistration(String userName) throws RegistrationException {
		if (userName != null) {
			logger.debug("RetrieveRegistration Started with User : {} ", userName);
			return registerRepository.findByUsername(userName);

		} else {
			logger.debug("RetrieveRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}

	}

	@Override
	public void updateRegistration(Register register) throws RegistrationException {

		if (register.getRegisterId() != null && register.getRegisterId() != 0 && register.getUsername() != null
				&& register.getCompanyName() != null && register.getAddress() != null
				&& register.getApplicationType() != null && register.getContactNumber() != null
				&& register.getDepartment() != null && register.getDesignation() != null
				&& register.getPassword() != null && register.getCountry() != null
				&& register.getInterestedAreas() != null && register.getName() != null && register.getState() != null) {

			Optional<Register> registerRepo = registerRepository.findById(register.getRegisterId());

			if (registerRepo.isPresent() && registerRepo.get().getRegisterId().equals(register.getRegisterId())
					&& registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				logger.debug("UpdatingRegistration Started");
				register.setUpdatedDate(LocalDateTime.now());
				register.setUpdatedBy(register.getUsername());
				registerRepository.save(register);
			} else {
				logger.debug("UpdatingRegistration is Faild , Because Given User not present");
				throw new RegistrationException("Given User not present");
			}

		} else {
			logger.debug("UpdatingRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}
	}

	@Override
	public Register updatePermission(RegisterPermissionRequest registerPermissionRequest)
			throws RegisterPermissionRequestException {
		logger.debug("updatePermission_function called");

		if (registerPermissionRequest != null && registerPermissionRequest.getAdminUserName() != null
				&& registerPermissionRequest.getPermission() != null
				&& registerPermissionRequest.getRegisterId() != null
				&& registerPermissionRequest.getRegisterId() != 0) {

			Optional<Register> registerRepo = registerRepository.findById(registerPermissionRequest.getRegisterId());
			
			if (registerRepo.isPresent()) {
				Register register = registerRepo.get();
				
				if (registerPermissionRequest.getPermission().equalsIgnoreCase("YES")) {

					logger.debug("Admin accepted Registration Permission");
					register.setApplicationType(registerPermissionRequest.getApplicationType());
					register.setPermission(registerPermissionRequest.getPermission());
					register.setPermissionBy(registerPermissionRequest.getAdminUserName());
					register.setUpdatedBy(registerPermissionRequest.getAdminUserName());
					register.setUpdatedDate(LocalDateTime.now());
					registerRepository.save(register);
					return register;
				} else {
					logger.debug("Admin Not-acepted Registration Permission");
					
					register.setApplicationType(registerPermissionRequest.getApplicationType());
					register.setComment(registerPermissionRequest.getComment());
					register.setPermission(registerPermissionRequest.getPermission());
					register.setUpdatedBy(registerPermissionRequest.getAdminUserName());
					registerRepository.save(register);
					return register;

				}

			} else {
				logger.debug("Given RegisterId not Avilable in DB");
				throw new RegisterPermissionRequestException("Given User not avilable");
			}

		} else {
			logger.debug("Given RegisterId not Avilable in DB");
			throw new RegisterPermissionRequestException("RegisterPermissionRequest has Invaild Input");
		}

	}

	@Override
	public List<Register> retrieveAllRegistration() throws RegistrationException {
		try {
			logger.debug("Started retrieveAllRegistration");
			return (List<Register>) registerRepository.findAll();

		} catch (Exception exception) {
			throw new RegistrationException("Retrieve function faild ExceptionMessage is : " + exception.getMessage());
		}
	}

}
