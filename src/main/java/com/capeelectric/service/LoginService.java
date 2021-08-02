package com.capeelectric.service;

import java.io.IOException;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.model.Register;

public interface LoginService {

	public Register findByUserName(String email) throws ForgotPasswordException, IOException;

	public Register updatePassword(String email, String password) throws UpdatePasswordException;

	public Register changePassword(String email, String oldPassword, String password) throws ChangePasswordException;

}
