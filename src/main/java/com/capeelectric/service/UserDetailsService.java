package com.capeelectric.service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;

public interface UserDetailsService {

	public User saveUser(User user) throws UserException;
}
