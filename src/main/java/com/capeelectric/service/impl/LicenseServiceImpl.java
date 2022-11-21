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
	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister) throws URISyntaxException {
		addDetailsTolicenseTable(viewerRegister);
		return viewerRegistrationRepository.save(viewerRegister); 
	}

	private void addDetailsTolicenseTable(ViewerRegister viewerRegister) throws URISyntaxException {
		
//		licenseRepository.findByUserName
		switch (viewerRegister.getSelectedProject()) {
		case "LV":{
			License license = new License();
			license.setViewerUserName(viewerRegister.getUsername());
			license.setInspectorUserName(viewerRegister.getAssignedBy());
			license.setLvSiteName(viewerRegister.getLvSiteName());
			licenseRepository.save(license);
			break;
		}
		case "LPS":{
			License license = new License();
			license.setViewerUserName(viewerRegister.getUsername());
			license.setInspectorUserName(viewerRegister.getAssignedBy());
			license.setLvSiteName(viewerRegister.getLvSiteName());
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
