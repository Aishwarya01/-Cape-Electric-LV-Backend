/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Light;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface LightRepository extends CrudRepository<Light, Integer>{
	
	Optional<Light> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<Light> findByLightId(Integer lightId);
	
}
