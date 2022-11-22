package com.capeelectric.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.AWSLVConfig;
import com.capeelectric.model.EmailContent;
import com.capeelectric.model.ViewerRegister;
import com.capeelectric.model.licence.License;
import com.capeelectric.model.licence.LpsLicense;
import com.capeelectric.model.licence.LpsRegister;
import com.capeelectric.model.licence.LvRegister;
import com.capeelectric.repository.LicenseRepository;
import com.capeelectric.repository.ViewerRegistrationRepository;
import com.capeelectric.service.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {

//	@Autowired
//	private LvRepository lvRepository;
//
//	@Autowired
//	private LpsRegisterRepository lpsRegisterRepository;
	
	@Autowired
	private ViewerRegistrationRepository viewerRegistrationRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private AWSLVConfig awsConfiguration;
	
//	@Override
//	public Optional<LvRegister> retrieveLvRegister(String userName) {
//		return lvRepository.findByUsername(userName);
//	}

//	@Override
//	public Optional<LpsRegister> retrieveLpsRegister(String userName) {
//		return lpsRegisterRepository.findByUsername(userName);
////		/return null;
//	}

	@Override
	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister) throws Exception {
		if (null != viewerRegister && viewerRegister.getUsername() != null) {
            Optional<ViewerRegister> viewerRegisterRepo = viewerRegistrationRepository
                    .findByUsername(viewerRegister.getUsername());
            if (!viewerRegisterRepo.isPresent()) {
                addDetailsTolicenseTable(viewerRegister);
                return viewerRegistrationRepository.save(viewerRegister);
            }
            else {
            	addDetailsTolicenseTable(viewerRegister);
            	return null;
//                throw new Exception(viewerRegister.getUsername() + " This user already Registered");
            }
        } else {
            throw new Exception("Username reqired");
        } 
	}

	private void addDetailsTolicenseTable(ViewerRegister viewerRegister) throws URISyntaxException {

		Optional<License> licenseRepo = licenseRepository.findByUserName(viewerRegister.getUsername());
		License license;
		if (licenseRepo.isPresent()) {
			license = licenseRepo.get();
		} else {
			license = new License();
		}

		switch (viewerRegister.getSelectedProject()) {
		case "LV": {

			license.setUserName(viewerRegister.getUsername());
			license.setLvSiteName(viewerRegister.getLvSiteName());
			licenseRepository.save(license);
			break;
		}
		case "LPS": {
			license.setUserName(viewerRegister.getUsername());
			license.setLpsclientName(viewerRegister.getLpsclientName());
			license.setLpsProjectName(viewerRegister.getLpsProjectName());
			licenseRepository.save(license);
			break;
		}
		}

	}
	
	//@Override
	private void sendLPSBasicData(HttpServletRequest request,LpsLicense license) throws URISyntaxException {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", request.getHeader("Authorization"));
		URI uri = new URI(awsConfiguration.getSendLPSBasicData());

		RequestEntity<LpsLicense> requestEntity = new RequestEntity<>(license,  headers, HttpMethod.POST, uri);
		ParameterizedTypeReference<EmailContent> typeRef = new ParameterizedTypeReference<EmailContent>() {};

		//restTemplate.exchange(requestEntity, typeRef);
		//logger.debug("Cape-Electric-AWS-Email service Response was successful");
		
	}

}
