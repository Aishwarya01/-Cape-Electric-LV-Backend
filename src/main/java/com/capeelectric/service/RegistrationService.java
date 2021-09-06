package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface RegistrationService {

	public Register addRegistration(Register inspector) throws RegistrationException;

	public Optional<Register> retrieveRegistration(String userName) throws RegistrationException;

	public void updateRegistration(Register inspector) throws RegistrationException;

	public void sendOtp(String userName, String mobileNumber) throws RegistrationException;

	public Register addViewerRegistration(Register register) throws RegistrationException;;

}
