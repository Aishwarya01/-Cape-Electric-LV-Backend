package com.capeelectric.service;

import java.util.List;
import java.util.Optional;

import com.capeelectric.exception.EarthConnectorException;
import com.capeelectric.model.EarthConnector;

public interface EarthConnectorService {
	public EarthConnector addEarthConnector(EarthConnector earthConnector) throws EarthConnectorException;

	public EarthConnector updateEarthConnector(EarthConnector earthConnector) throws EarthConnectorException;

	public Optional<EarthConnector> retrieveEarthConnectorData(String fileName, String nodeId)
			throws EarthConnectorException;

}
