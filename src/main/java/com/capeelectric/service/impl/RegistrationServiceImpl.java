/**
 * 
 */
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
 * @author capeelectricsoftware
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private RegistrationRepository registerRepository;

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
					register.setComment(registerPermissionRequest.getComment());
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
			throw new RegistrationException("Retrieve function failed ExceptionMessage is : " + exception.getMessage());
		}
	}

}
