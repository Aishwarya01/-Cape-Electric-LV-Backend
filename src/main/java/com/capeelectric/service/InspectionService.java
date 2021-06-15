package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.IpaoInspection;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface InspectionService {

	public void addInspectionDetails(IpaoInspection ipaoInspection) throws InspectionException;
	
	public List<IpaoInspection> retrieveInspectionDetails(String userName, Integer siteId) throws InspectionException;

}
