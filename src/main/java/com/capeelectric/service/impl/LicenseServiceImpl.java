package com.capeelectric.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capeelectric.model.Register;
import com.capeelectric.model.licence.LvRegister;
import com.capeelectric.repository.LvRepository;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.LicenseService;

public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private LvRepository lvRepository;
	
	@Override
	public Optional<LvRegister> retrieveLvRegister(String userName) {
 
		return lvRepository.findByUserName(userName);
	}

}
