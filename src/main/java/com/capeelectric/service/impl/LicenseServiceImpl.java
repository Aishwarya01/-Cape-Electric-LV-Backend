package com.capeelectric.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.capeelectric.repository.LpsRegisterRepository;
import com.capeelectric.repository.LvRepository;
import com.capeelectric.repository.ViewerRegistrationRepository;
import com.capeelectric.service.LicenseService;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.util.UserFullName;

@Service
public class LicenseServiceImpl implements LicenseService {

	@Autowired
	private LvRepository lvRepository;

	@Autowired
	private LpsRegisterRepository lpsRegisterRepository;
	
	@Autowired
	private ViewerRegistrationRepository viewerRegistrationRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AWSLVConfig awsConfiguration;
	
	@Override
	public Optional<LvRegister> retrieveLvRegister(String userName) {
		return lvRepository.findByUsername(userName);
	}

	@Override
	public Optional<LpsRegister> retrieveLpsRegister(String userName) {
		return lpsRegisterRepository.findByUsername(userName);
//		/return null;
	}

	@Override
	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister) throws URISyntaxException {
		addProject(viewerRegister);
		return viewerRegistrationRepository.save(viewerRegister); 
	}

	private void addProject(ViewerRegister viewerRegister) throws URISyntaxException {
		switch (viewerRegister.getProject()) {
		case "lvPage":
			break;
		case "lpsPage":{
			License license = viewerRegister.getLicense().get(0);
			if (null !=license) {
				LpsLicense lpsLicense = new LpsLicense();
				lpsLicense.setLpsclientName(license.getLpsclientName());
				lpsLicense.setLpsProjectName(license.getLpsProjectName());
				lpsLicense.setUserName(viewerRegister.getAssignedBy());
				lpsLicense.setMailId(viewerRegister.getUsername());
				sendLPSBasicData(lpsLicense);
			}
			
			break;
		}
		case "EMC":
			break;
		case "RISK":
			break;
		}
		
	}
	
	//@Override
	private void sendLPSBasicData(LpsLicense license) throws URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		URI uri = new URI(awsConfiguration.getSendLPSBasicData());

		RequestEntity<LpsLicense> requestEntity = new RequestEntity<>(license, headers, HttpMethod.POST, uri);
		ParameterizedTypeReference<EmailContent> typeRef = new ParameterizedTypeReference<EmailContent>() {};

		restTemplate.exchange(requestEntity, typeRef);
		//logger.debug("Cape-Electric-AWS-Email service Response was successful");
		
	}

}
