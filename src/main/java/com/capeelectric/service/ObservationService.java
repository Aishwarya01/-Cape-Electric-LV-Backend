package com.capeelectric.service;

import java.util.List;

import com.capeelectric.exception.ObservationException;
import com.capeelectric.model.ObservationComponent;

public interface ObservationService {

	public void addObservation(ObservationComponent observationComponent) throws ObservationException;

	public void updateObservation(ObservationComponent observationComponent) throws ObservationException;

	public ObservationComponent retrieveObservation(String userName, Integer siteId, String observationComponent) throws ObservationException;

}
