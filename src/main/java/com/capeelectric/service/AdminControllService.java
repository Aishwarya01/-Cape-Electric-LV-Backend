package com.capeelectric.service;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;
import com.capeelectric.model.CustomAdminDetails;

public interface AdminControllService {

	public Admin updatePassword(String email, String password) throws UpdatePasswordException;

	public Admin findByAdmin(String email) throws ForgotPasswordException;

	public Admin changePassword(String email, String oldPassword, String password) throws ChangePasswordException;

	public Admin saveAdmin(Admin admin) throws UserException;

	public Admin updateAdminDetails(Admin admin) throws UserException;

	public void deleteAdmin(Integer adminId) throws UserException;

	public Admin retrieveAdminInformation(String email) throws UserException;



	









}
