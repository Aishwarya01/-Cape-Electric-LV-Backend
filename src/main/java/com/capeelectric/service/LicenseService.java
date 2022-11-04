package com.capeelectric.service;

import java.util.Optional;

import com.capeelectric.model.ViewerRegister;
import com.capeelectric.model.licence.LpsRegister;
import com.capeelectric.model.licence.LvRegister;

public interface LicenseService {

	public Optional<LvRegister> retrieveLvRegister(String userName);

	public Optional<LpsRegister> retrieveLpsRegister(String userName);

	public ViewerRegister addViewerRegistration(ViewerRegister viewerRegister);

}
