package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface InspectionService {

	public void addInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException;
	
	public List<PeriodicInspection> retrieveInspectionDetails(String userName, Integer siteId) throws InspectionException;

	public void updateInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException;

	public PeriodicInspection sendComments(String userName, Integer siteId, String comments) throws InspectionException;

	public String replyComments(String userName, Integer siteId, String comments) throws InspectionException;

}
