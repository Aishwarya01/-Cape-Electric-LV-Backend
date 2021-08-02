package com.capeelectric.service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;

public interface AdminControllService {

//	public List<User> getAllUser() throws UserException;

//	public void updateAccessUserslist(List<User> user) throws UserException;

	public Admin saveAdmin(Admin admin) throws UserException;

	public Admin updateAdminDetails(Admin admin) throws UserException;

	public void deleteByAdmin(Integer adminId) throws UserException;

	public Admin retrieveAdminInformation(String email) throws UserException;



}
