package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;

public interface AdminControllService {

	public List<User> getAllUser() throws UserException;

	public void updateAccessUserslist(List<User> user) throws UserException;

}
