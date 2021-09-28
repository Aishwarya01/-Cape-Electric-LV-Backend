package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capeelectric.config.OtpConfig;
import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.RegistrationException;
import com.capeelectric.model.Register;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.service.RegistrationService;
import com.capeelectric.util.Constants;

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
	private SiteServiceImpl siteServiceImpl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${number.of.licence}")
	private String numberOfLicence;
	
	private Set<SitePersons> setSitePersons;
	
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
						register.setNoOfLicence(numberOfLicence);
						register.setPermission(Constants.before_Approve_Permission);
					}
					register.setCreatedDate(LocalDateTime.now());
					register.setUpdatedDate(LocalDateTime.now());
					register.setCreatedBy(register.getUsername());
					register.setUpdatedBy(register.getUsername());
					register.setNoOfLicence("0");
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
	public Register addViewerRegistration(Register register) throws RegistrationException, CompanyDetailsException {
		logger.debug("AddingRegistration Starts with User : {} ", register.getUsername());
		if (register.getUsername() != null && register.getCompanyName() != null && register.getAddress() != null
				&& register.getContactNumber() != null && register.getDepartment() != null
				&& register.getDesignation() != null && register.getName() != null && register.getState() != null
				&& register.getSiteName() != null) {

			Optional<Register> registerRepo = registerRepository.findByUsername(register.getUsername());
			if (!registerRepo.isPresent()
					|| !registerRepo.get().getUsername().equalsIgnoreCase(register.getUsername())) {
				if (isValidIndianMobileNumber(register.getContactNumber())) {
					register.setCreatedDate(LocalDateTime.now());
					register.setPermission("YES");
					register.setUpdatedDate(LocalDateTime.now());
					register.setCreatedBy(register.getAssignedBy());
					register.setUpdatedBy(register.getAssignedBy());
					reduceLicence(register.getAssignedBy(),register.getSiteName());
					Register createdRegister = registerRepository.save(register);
					saveSiteInfo(createdRegister);
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
				Register registerDetails = registerRepo.get();
				registerDetails.setUpdatedDate(LocalDateTime.now());
				if (register.getRole().equalsIgnoreCase("INSPECTOR")) {
					registerDetails.setUpdatedBy(register.getUsername());
					registerRepository.save(registerDetails);
				} else {
					registerDetails.setUpdatedBy(register.getAssignedBy());
					if (isLicenseUpdate) {
						reduceLicence(register.getAssignedBy(), register.getSiteName());
						saveSiteInfo(register);
						registerRepository.save(registerDetails);
					}
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
							register.setUpdatedBy(userName);
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
			if (registerRepo.isPresent() && registerRepo.get().getUsername().equalsIgnoreCase(userName)) {
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
	
	
	private void reduceLicence(String inspectorUserName, String siteName) throws RegistrationException {
		try {
			Optional<Register> inspectorInfo = registerRepository.findByUsername(inspectorUserName);
			Register inspector = inspectorInfo.get();
			if (siteName != null) {
				if (inspector.getSiteName() == null) {
					inspector.setSiteName(siteName);
				} else {
					inspector.setSiteName(inspector.getSiteName() + "," + siteName);
				}
			} else {
				throw new RegistrationException("Site_Name Invalid Input");
			}
			inspector.setNoOfLicence(String.valueOf(Integer.parseInt(inspector.getNoOfLicence()) - 1));
			inspector.setUpdatedBy(inspectorUserName);
			inspector.setUpdatedDate(LocalDateTime.now());
			registerRepository.save(inspector);
		} catch (Exception e) {
			throw new RegistrationException("Inspector License updating failed");
		}
	}
	
	private void saveSiteInfo(Register createdRegister) throws CompanyDetailsException, RegistrationException {
		SitePersons sitePersons = null;
		setSitePersons = new HashSet<SitePersons>();
		if (createdRegister != null) {
			Optional<Register> registerRepo = registerRepository.findByUsername(createdRegister.getAssignedBy());
			if (registerRepo.isPresent() && registerRepo.get() != null
					&& registerRepo.get().getUsername().equalsIgnoreCase(createdRegister.getAssignedBy())) {
				Register register = registerRepo.get();
				Site site = new Site();
				site.setCountry(register.getCountry());
				site.setSite(createdRegister.getSiteName());
				site.setState(register.getState());
				site.setAddressLine_1(register.getAddress());
				site.setZipCode(register.getPinCode());
				site.setLandMark(register.getDistrict());
				site.setUserName(register.getUsername());
				site.setCompanyName(createdRegister.getCompanyName());
				site.setDepartmentName(createdRegister.getDepartment());
				sitePersons = new SitePersons();

				sitePersons.setPersonInchargeEmail(createdRegister.getUsername());
				sitePersons.setSiteName(createdRegister.getSiteName());
				sitePersons.setPersonIncharge(createdRegister.getName());
				sitePersons.setContactNo(createdRegister.getContactNumber());
				sitePersons.setDesignation(createdRegister.getDesignation());
				sitePersons.setInActive(true);
				sitePersons.setSite(site);
				setSitePersons.add(sitePersons);
				site.setSitePersons(setSitePersons);
				siteServiceImpl.addSite(site);
			} else {
				throw new RegistrationException("Site_creation Faild ,Given inspector UserName  does not Exist");
			}
		} else {
			throw new RegistrationException("Registration Failed");
		}

	}

}
