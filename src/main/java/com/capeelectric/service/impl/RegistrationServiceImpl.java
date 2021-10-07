package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegisterPermissionRequestException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.request.RegisterPermissionRequest;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.UserFullName;

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
	
	@Autowired
	private UserFullName userFullName;
	
	@Override
	public Register addRegistration(Register register) throws RegistrationException {
		logger.debug("AddingRegistration Starts with User : {} ", register.getUsername());
		if (register.getUsername() != null && register.getCompanyName() != null && register.getAddress() != null
				&& register.getApplicationType() != null && register.getContactNumber() != null
				&& register.getDepartment() != null && register.getDesignation() != null && register.getName() != null
				&& register.getState() != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(register.getUsername());
			if (!registerRepo.isPresent()
					|| !registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				if (isValidIndianMobileNumber(register.getContactNumber())) {
					if (register.getRole() != null && register.getRole().equalsIgnoreCase("INSPECTOR")) {
						register.setNoOfLicence("0");
						register.setPermission(Constants.before_Approve_Permission);
					}
					register.setCreatedDate(LocalDateTime.now());
					register.setUpdatedDate(LocalDateTime.now());
					register.setCreatedBy(register.getName());
					register.setUpdatedBy(register.getName());
					Register createdRegister = registerRepository.save(register);
					logger.debug("Sucessfully Registration Information Saved");
					return createdRegister;
				} else {
					logger.debug(
							isValidIndianMobileNumber(register.getContactNumber()) + "  Given MobileNumber is Invalid");
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
	@Transactional
	public Register addViewerRegistration(Register viewer) throws RegistrationException, CompanyDetailsException {
		logger.debug("AddingRegistration Starts with User : {} ", viewer.getUsername());
		if (viewer.getUsername() != null && viewer.getCompanyName() != null && viewer.getAddress() != null
				&& viewer.getContactNumber() != null && viewer.getDepartment() != null
				&& viewer.getDesignation() != null && viewer.getName() != null && viewer.getState() != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(viewer.getUsername());
			if (!registerRepo.isPresent() || !registerRepo.get().getUsername().equalsIgnoreCase(viewer.getUsername())) {
				if (isValidIndianMobileNumber(viewer.getContactNumber())) {
					viewer.setCreatedDate(LocalDateTime.now());
					viewer.setPermission("YES");
					viewer.setUpdatedDate(LocalDateTime.now());
					viewer.setCreatedBy(viewer.getName());
					viewer.setUpdatedBy(viewer.getName());
					//reduceLicence(viewer.getAssignedBy(),viewer.getSiteName());
					Register createdRegister = registerRepository.save(viewer);
					// saveSiteInfo(createdRegister);
					logger.debug("Sucessfully Registration Information Saved");
					return createdRegister;
				} else {
					logger.debug(
							isValidIndianMobileNumber(viewer.getContactNumber()) + "  Given MobileNumber is Invalid");
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
			Optional<Register> registerRepo = registerRepository.findByUsername(userName);
			if (registerRepo.isPresent()) {
				return registerRepository.findByUsername(userName);
			} else {
				throw new RegistrationException("Email Id doesn't exist!");
			}
		} else {
			logger.debug("RetrieveRegistration is Faild , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}

	}

	@Override
	@Transactional
	public void updateRegistration(Register register,Boolean isLicenseUpdate) throws RegistrationException, CompanyDetailsException {

		if (register.getRegisterId() != null && register.getRegisterId() != 0 && register.getUsername() != null
				&& register.getCompanyName() != null && register.getAddress() != null
				&& register.getContactNumber() != null && register.getDepartment() != null
				&& register.getDesignation() != null && register.getCountry() != null && register.getName() != null
				&& register.getState() != null&& isLicenseUpdate != null) {

			Optional<Register> registerRepo = registerRepository.findById(register.getRegisterId());

			if (registerRepo.isPresent() && registerRepo.get().getRegisterId().equals(register.getRegisterId())
					&& registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				logger.debug("UpdatingRegistration Started");
				
				register.setUpdatedDate(LocalDateTime.now());
				if (register.getRole().equalsIgnoreCase("INSPECTOR")) {
					register.setUpdatedBy(userFullName.findByUserName(register.getUsername()));
					registerRepository.save(register);
				} else {
					//if (isLicenseUpdate) {
						//reduceLicence(register.getAssignedBy(), register.getSiteName());
						//saveSiteInfo(register);
					//	registerRepository.save(register);
					//} else {
					//}
					register.setUpdatedBy(userFullName.findByUserName(register.getAssignedBy()));
					registerRepository.save(register);
				}

			} else {
				logger.debug("UpdatingRegistration is Failed , Because Given User not present");
				throw new RegistrationException("Given User not present");
			}

		} else {
			logger.debug("UpdatingRegistration is Failed , Because Invalid Inputs");
			throw new RegistrationException("Invalid Inputs");
		}
	}

	@Override
	public void sendOtp(String userName, String mobileNumber) throws RegistrationException {

		if (userName != null && mobileNumber != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(userName);
			if (registerRepo.isPresent() && registerRepo.get() != null) {
				if (registerRepo.get().getPermission().equalsIgnoreCase("Yes")) {
					if (registerRepo.get().getContactNumber().contains(mobileNumber)) {
						if (isValidIndianMobileNumber(mobileNumber)) {
							String sessionKey = otpSend(mobileNumber);
							Register register = registerRepo.get();
							register.setOtpSessionKey(sessionKey);
							register.setUpdatedDate(LocalDateTime.now());
							register.setUpdatedBy(userFullName.findByUserName(userName));
							registerRepository.save(register);
						} else {
							throw new RegistrationException("Invalid MobileNumber");
						}
					} else {
						throw new RegistrationException("Enter registered MobileNumber");
					}
				} else {
					throw new RegistrationException("Admin not approved for Your registration");
				}
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

	@Override
	public void updateLicence(String userName, String numoflicence) throws RegistrationException {

		if (userName != null && numoflicence != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(userName);
			if (registerRepo.isPresent() && registerRepo.get().getUsername() != null
					&& registerRepo.get().getUsername().equalsIgnoreCase(userName)) {
				Register register = registerRepo.get();
				register.setNoOfLicence(numoflicence);
				register.setUpdatedDate(LocalDateTime.now());
				register.setUpdatedBy(userName);
				registerRepository.save(register);
			} else {
				throw new RegistrationException("Given UserName does not Exist");
			}

		} else {
			throw new RegistrationException("Invalid Input");
		}
	}

	@Override
	public String sendNewOtp(String mobileNumber) throws RegistrationException {
		return otpSend(mobileNumber);
	}
	
	@Override
	public Register updatePermission(RegisterPermissionRequest registerPermissionRequest)
			throws RegisterPermissionRequestException {
		logger.debug("updatePermission_function called");

		if (registerPermissionRequest != null && registerPermissionRequest.getAdminUserName() != null
				&& registerPermissionRequest.getPermission() != null
				&& registerPermissionRequest.getRegisterId() != null
				&& registerPermissionRequest.getRegisterId() != 0) {

			Optional<Register> registerRepo = registerRepository.findById(registerPermissionRequest.getRegisterId());
			
			if (registerRepo.isPresent()) {
				Register register = registerRepo.get();
				
				if (registerPermissionRequest.getPermission().equalsIgnoreCase("YES")) {

					logger.debug("Admin accepted Registration Permission");
					register.setApplicationType(registerPermissionRequest.getApplicationType());
					register.setComment(registerPermissionRequest.getComment());
					register.setPermission(registerPermissionRequest.getPermission());
					register.setPermissionBy(registerPermissionRequest.getAdminUserName());
					register.setUpdatedBy(registerPermissionRequest.getAdminUserName());
					register.setUpdatedDate(LocalDateTime.now());
					registerRepository.save(register);
					return register;
				} else {
					logger.debug("Admin Not-acepted Registration Permission");
					
					register.setApplicationType(registerPermissionRequest.getApplicationType());
					register.setComment(registerPermissionRequest.getComment());
					register.setPermission(registerPermissionRequest.getPermission());
					register.setUpdatedBy(registerPermissionRequest.getAdminUserName());
					registerRepository.save(register);
					return register;

				}

			} else {
				logger.debug("Given RegisterId not Avilable in DB");
				throw new RegisterPermissionRequestException("Given User not avilable");
			}

		} else {
			logger.debug("Given RegisterId not Avilable in DB");
			throw new RegisterPermissionRequestException("RegisterPermissionRequest has Invaild Input");
		}

	}

	@Override
	public List<Register> retrieveAllRegistration() throws RegistrationException {
		try {
			logger.debug("Started retrieveAllRegistration");
			return (List<Register>) registerRepository.findAll();

		} catch (Exception exception) {
			throw new RegistrationException("Retrieve function failed ExceptionMessage is : " + exception.getMessage());
		}
	}
}
