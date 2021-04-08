package com.capeelectric.service;

import java.util.List;

import com.capeelectric.model.ApplicationTypes;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface ApplicationTypesService {

	public List<ApplicationTypes> retrieveTypes();
	
	public ApplicationTypes addApplicationTypes(ApplicationTypes types);
}