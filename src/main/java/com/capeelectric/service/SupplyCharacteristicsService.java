package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristics;

/**
  *
  * @author capeelectricsoftware
  *
  */
public interface SupplyCharacteristicsService {
	public void addCharacteristics(SupplyCharacteristics site) throws SupplyCharacteristicsException;

	public List<SupplyCharacteristics> retrieveCharacteristics(String userName, Integer siteId)throws SupplyCharacteristicsException;

}
