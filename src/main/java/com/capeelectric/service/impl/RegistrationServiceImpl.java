package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.RegistrationService;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	
	private static final String SESSION_TITLE = ".*\"Details\":\"(.+)\".*";
	
	@Autowired
	private OtpConfig otpConfig;
	
	@Autowired
	private RegistrationRepository registerRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Register addRegistration(Register register) throws RegistrationException {
		logger.debug("AddingRegistration Starts with User : {} ", register.getUsername());
		if (register.getUsername() != null && register.getCompanyName() != null && register.getAddress() != null
				&& register.getApplicationType() != null && register.getContactNumber() != null
				&& register.getDepartment() != null && register.getDesignation() != null
				&& register.getInterestedAreas() != null && register.getName() != null && register.getState() != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(register.getUsername());
			if (!registerRepo.isPresent()
					|| !registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				if (isValidIndianMobileNumber(register.getContactNumber())) {
					register.setCreatedDate(LocalDateTime.now());
					register.setPermission("NOT_AUTHORIZED");
					register.setRole("ROLE");
					register.setUpdatedDate(LocalDateTime.now());
					register.setCreatedBy(register.getUsername());
					register.setUpdatedBy(register.getUsername());
					register.setOtpSessionKey(otpSend(register.getContactNumber()));
					Register createdRegister = registerRepository.save(register);
					logger.debug("Sucessfully Registration Information Saved");
					return createdRegister;
				} else {
					logger.debug(isValidIndianMobileNumber(register.getContactNumber())+"  Given MobileNumber is Invalid");
					throw new RegistrationException("Invalid MobileNumber");
				}

			} else {
				logger.debug("Given UserName Already Present");
				throw new RegistrationException("Given UserName Already Present");
			}

		} else {
			logger.debug("AddingRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}
	}

	@Override
	public Optional<Register> retrieveRegistration(String userName) throws RegistrationException {
		if (userName != null) {
			logger.debug("RetrieveRegistration Started with User : {} ", userName);
			return registerRepository.findByUsername(userName);

		} else {
			logger.debug("RetrieveRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}

	}

	@Override
	public void updateRegistration(Register register) throws RegistrationException {

		if (register.getRegisterId() != null && register.getRegisterId() != 0 && register.getUsername() != null
				&& register.getCompanyName() != null && register.getAddress() != null
				&& register.getApplicationType() != null && register.getContactNumber() != null
				&& register.getDepartment() != null && register.getDesignation() != null
				&& register.getPassword() != null && register.getCountry() != null
				&& register.getInterestedAreas() != null && register.getName() != null && register.getState() != null) {

			Optional<Register> registerRepo = registerRepository.findById(register.getRegisterId());

			if (registerRepo.isPresent() && registerRepo.get().getRegisterId().equals(register.getRegisterId())
					&& registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				logger.debug("UpdatingRegistration Started");
				register.setUpdatedDate(LocalDateTime.now());
				register.setUpdatedBy(register.getUsername());
				registerRepository.save(register);
			} else {
				logger.debug("UpdatingRegistration is Faild , Because Given User not present");
				throw new RegistrationException("Given User not present");
			}

		} else {
			logger.debug("UpdatingRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}
	}

	@Override
	public String resendOtp(String mobileNumber) throws RegistrationException {
		if (mobileNumber != null) {
			if (isValidIndianMobileNumber(mobileNumber)) {
				return otpSend(mobileNumber);
			} else {
				throw new RegistrationException("Invalid MobileNumber");
			}
		} else {
			throw new RegistrationException("Invalid Input");
		}

	}
	
	private boolean isValidIndianMobileNumber(String mobileNumber) {
		Pattern p = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$");
		Matcher m = p.matcher(mobileNumber);
		return (m.find() && m.group().equals(mobileNumber));
	}
	
	private String otpSend(String mobileNumber) throws RegistrationException {

		ResponseEntity<String> sendOtpResponse = restTemplate.exchange(otpConfig.getSendOtp() + mobileNumber, HttpMethod.GET, null,
				String.class);

		if (!sendOtpResponse.getBody().matches("(.*)Success(.*)")) {
			throw new RegistrationException(sendOtpResponse.getBody());
		}

		return sendOtpResponse.getBody().replaceAll(SESSION_TITLE, "$1");
	}


}
