package com.capeelectric.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.ViewerRegister;
import com.capeelectric.model.licence.LpsRegister;
import com.capeelectric.model.licence.LvRegister;
import com.capeelectric.repository.LpsRegisterRepository;
import com.capeelectric.repository.LvRepository;
import com.capeelectric.repository.ViewerRegistrationRepository;
import com.capeelectric.service.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private LvRepository lvRepository;

	//@Autowired
	//private LpsRegisterRepository lpsRegisterRepository;
	
	@Autowired
	private ViewerRegistrationRepository viewerRegistrationRepository;


	@Override
	public Optional<LvRegister> retrieveLvRegister(String userName) {
		return lvRepository.findByUsername(userName);
	}

	@Override
	public Optional<LpsRegister> retrieveLpsRegister(String userName) {
		//return lpsRegisterRepository.findByUsername(userName);
		return null;
	}

	@Override
	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister) {
		return viewerRegistrationRepository.save(viewerRegister); 
	}

}
