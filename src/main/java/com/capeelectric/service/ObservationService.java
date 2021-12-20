package com.capeelectric.service;

import com.capeelectric.exception.ObservationException;
import com.capeelectric.model.ObservationAllComponent;
import com.capeelectric.model.ObservationComponent;

public interface ObservationService {

	public void addObservation(ObservationComponent observationComponent) throws ObservationException;

	public void updateObservation(ObservationComponent observationComponent) throws ObservationException;

	public ObservationComponent retrieveObservation(String userName, Integer siteId, String observationComponent)
			throws ObservationException;

	public ObservationAllComponent retrieveObservationsInSummary(String userName, Integer siteId)
			throws ObservationException;

}
