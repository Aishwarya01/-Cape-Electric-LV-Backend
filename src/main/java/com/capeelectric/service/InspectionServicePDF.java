package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;

public interface InspectionServicePDF {

	public List<PeriodicInspection> retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException;
}