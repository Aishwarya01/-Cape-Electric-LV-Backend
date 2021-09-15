package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.DecimalConversionException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristicComment;
import com.capeelectric.model.SupplyCharacteristics;

/**
  *
  * @author capeelectricsoftware
  *
  */
public interface SupplyCharacteristicsService {
	public void addCharacteristics(SupplyCharacteristics site)
			throws SupplyCharacteristicsException, DecimalConversionException;

	public List<SupplyCharacteristics> retrieveCharacteristics(String userName, Integer siteId)
			throws SupplyCharacteristicsException;

	public void updateCharacteristics(SupplyCharacteristics supplyCharacteristics)
			throws SupplyCharacteristicsException;

	public void sendComments(String userName, Integer siteId, SupplyCharacteristicComment supplyCharacteristicComment)
			throws SupplyCharacteristicsException;

	public String replyComments(String userName, Integer siteId,
			SupplyCharacteristicComment supplyCharacteristicComment) throws SupplyCharacteristicsException;

	public void approveComments(String userName, Integer siteId,
			SupplyCharacteristicComment supplyCharacteristicComment) throws SupplyCharacteristicsException;

}
