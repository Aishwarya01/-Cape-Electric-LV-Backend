package com.capeelectric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.ApplicationTypes;
import com.capeelectric.repository.ApplicationTypesRepository;
import com.capeelectric.service.ApplicationTypesService;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class ApplicationTypesServiceImpl implements ApplicationTypesService {

	@Autowired
	private ApplicationTypesRepository repository;
	
	@Override
	public List<ApplicationTypes> retrieveTypes() {
		return (List<ApplicationTypes>) repository.findAll();
	}

	@Override
	public ApplicationTypes addApplicationTypes(ApplicationTypes types) {
		return repository.save(types);
	}
	
	

}