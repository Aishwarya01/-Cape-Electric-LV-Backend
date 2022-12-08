package com.capeelectric.service.impl;

import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.model.ViewerRegister;
import com.capeelectric.model.licence.License;
import com.capeelectric.repository.LicenseRepository;
import com.capeelectric.repository.ViewerRegistrationRepository;
import com.capeelectric.service.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {

	private static final Logger logger = LoggerFactory.getLogger(LicenseServiceImpl.class);
	
	@Autowired
	private ViewerRegistrationRepository viewerRegistrationRepository;
	
	@Autowired
	private LicenseRepository licenseRepository;

	/**
	 * @parm viewerRegister
	 * addViewerregisteration function adding new viewer information to register table from inspector
	 * and this function updating application types based inspector adding project 
	*/
	@Override
	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister) throws Exception {
		if (null != viewerRegister && viewerRegister.getUsername() != null) {
			Optional<ViewerRegister> viewerRegisterRepo = viewerRegistrationRepository
					.findByUsername(viewerRegister.getUsername());
			try {
				if (!viewerRegisterRepo.isPresent()) {
					ViewerRegister register = viewerRegistrationRepository.save(viewerRegister);
					addDetailsTolicenseTable(viewerRegister);
					return register;
				} else {
					addDetailsTolicenseTable(viewerRegister);
					ViewerRegister viewerReg = viewerRegisterRepo.get();
					viewerReg.setApplicationType(
							viewerRegisterRepo.get().getApplicationType() + "," + viewerRegister.getApplicationType());
					viewerRegistrationRepository.save(viewerReg);
//					addDetailsTolicenseTable(viewerRegister);
					return null;
				}

			} catch (Exception message) {
				logger.debug("adding Registeration failed " + message.getMessage());
				throw new Exception("Adding Registeration failed");
			}
		} else {
			logger.debug("Username reqired");
			throw new Exception("Username reqired");
		}
	}

	private void addDetailsTolicenseTable(ViewerRegister viewerRegister) throws URISyntaxException {

		Optional<License> licenseRepo = licenseRepository.findByUserName(viewerRegister.getAssignedBy());

		switch (viewerRegister.getSelectedProject()) {
		case "LV": {
			licenseRepo.get()
					.setLvNoOfLicence(Integer.toString(Integer.parseInt(licenseRepo.get().getLvNoOfLicence()) - 1));
			break;
		}
		case "LPS": {
			licenseRepo.get()
					.setLpsNoOfLicence(Integer.toString((Integer.parseInt(licenseRepo.get().getLpsNoOfLicence()) - 1)));
			break;
		}
		}
		licenseRepository.save(licenseRepo.get());

	}

}
