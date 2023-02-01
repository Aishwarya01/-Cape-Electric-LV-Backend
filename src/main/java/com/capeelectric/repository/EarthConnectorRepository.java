package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EarthConnector;

public interface EarthConnectorRepository extends CrudRepository<EarthConnector, Integer> {
	public Optional<EarthConnector> findByFileNameAndNodeId(String fileName, String nodeId);

	public Optional<EarthConnector> findByEarthconnectorid(Integer earthconnectorid);

}
