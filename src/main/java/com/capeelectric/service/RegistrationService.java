package com.capeelectric.service;

import java.util.List;

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

	public Register updatePermission(RegisterPermissionRequest registerPermissionRequest) throws RegisterPermissionRequestException;

	public List<Register> retrieveAllRegistration() throws RegistrationException;

}
