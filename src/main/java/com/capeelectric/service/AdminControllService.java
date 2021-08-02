package com.capeelectric.service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;

public interface AdminControllService {

//	public List<User> getAllUser() throws UserException;

//	public void updateAccessUserslist(List<User> user) throws UserException;

	public Admin saveAdmin(Admin admin) throws UserException;

	public Admin updateManagementDetails(Admin admin) throws UserException;

	public void deleteById(Integer adminId) throws UserException;

	public Admin retrieveManagementInformation(String email) throws UserException;



}
