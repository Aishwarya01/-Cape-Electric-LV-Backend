package com.capeelectric.service;


import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.request.RegisterPermissionRequest;



/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface RegistrationService {


	public Register addRegistration(Register inspector) throws RegistrationException;

	public Optional<Register> retrieveRegistration(String userName) throws RegistrationException;

	public void updateRegistration(Register inspector, Boolean isLicenseUpdate) throws RegistrationException, CompanyDetailsException;

	public void sendOtp(String userName, String mobileNumber) throws RegistrationException;

	public Register addViewerRegistration(Register register) throws RegistrationException, CompanyDetailsException;

	public void updateLicence(String userName, String numoflicence) throws RegistrationException;
	
	public String sendNewOtp(String mobileNumber) throws RegistrationException;

	public Register updatePermission(RegisterPermissionRequest registerPermissionRequest) throws RegisterPermissionRequestException;
	
	public List<Register> retrieveAllRegistration() throws RegistrationException;

}
