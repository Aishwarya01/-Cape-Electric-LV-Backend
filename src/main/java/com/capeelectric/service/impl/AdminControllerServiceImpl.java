package com.capeelectric.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;
import com.capeelectric.model.User;
import com.capeelectric.repository.AdminControllRepositary;
import com.capeelectric.service.AdminControllService;

@Service
public class AdminControllerServiceImpl implements AdminControllService {

	private static final Logger logger = LoggerFactory.getLogger(AdminControllerServiceImpl.class);

	@Autowired
	private AdminControllRepositary adminControllRepositary;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Admin saveAdmin(Admin admin) throws UserException {
		logger.debug("Save Admin Starts");
		if (admin.getUsername() != null) {
			Optional<Admin> createdAdmin = adminControllRepositary.findByUsername(admin.getUsername	());
			if (createdAdmin.isPresent() && createdAdmin.get() != null && createdAdmin.get().isAdminexist()) {
				logger.debug("Save Admin Ends");
				throw new UserException("Admin already available");
			} else {
				String password = admin.getPassword();
				admin.setPassword(passwordEncoder.encode(password));
				admin.setAdminexist(true);
				admin.setCreationdate(LocalDateTime.now());
				admin.setUpdateddate(LocalDateTime.now());
				logger.debug("Save Admin Ends");
				return adminControllRepositary.save(admin);
			}
		} else {
			throw new UserException("invalid input");
		}
	}

	public Admin retrieveAdminInformation(String email) throws UserException {
		logger.debug("Retrieve Admin Starts");
		Optional<Admin> retrievedAdmin = adminControllRepositary.findByUsername(email);
		if (retrievedAdmin.isPresent() && retrievedAdmin.get() != null && retrievedAdmin.get().isAdminexist()) {
			return retrievedAdmin.get();
		} else {
			throw new UserException("Admin not available");
		}
	}

	@Override
	public Admin updateAdminDetails(Admin admin) throws UserException  {
		logger.debug("Update Management Profile Starts");
		admin.setUpdateddate(LocalDateTime.now());
		logger.debug("Update Management Profile Ends");
		return adminControllRepositary.save(admin);
	}

	@Override
	public void deleteAdmin(Integer adminId) throws UserException {
		if (adminId != null && adminId != 0) {

			Optional<Admin> adminRepo = adminControllRepositary.findById(adminId);

			if (adminRepo.isPresent() && adminRepo != null) {

				adminControllRepositary.deleteById(adminId);
			} else {
				throw new UserException(adminId + " : this adminid not present");
			}

		} else {
			throw new UserException("invalid input");
		}

	}

	@Override
   public Admin updatePassword(String email, String password) throws UpdatePasswordException {
		// TODO: Email triggering
		logger.debug("Update Admin Starts");
		if (email != null && password != null) {
			Admin admin = adminControllRepositary.findByUsername(email).get();
			if (admin != null && admin.isAdminexist()) {
				admin.setPassword(passwordEncoder.encode(password));
				admin.setUpdateddate(LocalDateTime.now());
				logger.debug("Update Admin Ends");
				return adminControllRepositary.save(admin);
			}
			else {
				logger.debug("Update Admin Ends");
				throw new UpdatePasswordException("Admin Not available");
			}
		} else {
			logger.debug("Update Admin Ends");
			throw new UsernameNotFoundException("adminname not valid");
		}
	}
	@Override
	public Admin changePassword(String email, String oldPassword, String password) throws ChangePasswordException {
		logger.debug("Change Password Starts");
		Admin retrieveAdmin = adminControllRepositary.findByUsername(email).get();
		if(!passwordEncoder.matches(oldPassword, retrieveAdmin.getPassword())) {
			logger.debug("Change Password Ends");
			throw new ChangePasswordException("Old password is not matching with encoded password");
		} else if(oldPassword.equalsIgnoreCase(password)) {
			logger.debug("Change Password Ends");
			throw new ChangePasswordException("Old password cannot be entered as new password");
		} else {
			if (retrieveAdmin != null && retrieveAdmin.isAdminexist()) {
				retrieveAdmin.setPassword(passwordEncoder.encode(password));
				retrieveAdmin.setUpdateddate(LocalDateTime.now());
				logger.debug("Update Admin Ends");
				return adminControllRepositary.save(retrieveAdmin);
			}
		}
		return null;
	}
	@Override
	public Admin findByAdmin(String email) throws ForgotPasswordException{
		logger.debug("Find By Admin Name Starts");
		if (email != null) {
			Optional<Admin> optionalAdmin = adminControllRepositary.findByUsername(email);
			if (optionalAdmin != null && optionalAdmin.isPresent() && optionalAdmin.get()!= null) {
				logger.debug("Find By User Name Ends");
				return optionalAdmin.get();
			} else {
				logger.debug("Find By Admin Name Ends");
				throw new ForgotPasswordException("Email is not available with us");
			}
		} else {
			logger.debug("Find By Admin Name Ends");
			throw new ForgotPasswordException("Email is required");
		}
	}

}
