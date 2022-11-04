package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.model.licence.LvRegister;

public interface LicenseService {

	public Optional<LvRegister> retrieveLvRegister(String userName);

}
