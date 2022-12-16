/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.PortableAppliance;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface PortableApplianceRepository extends CrudRepository<PortableAppliance, Integer>{
	
	Optional<PortableAppliance> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<PortableAppliance> findByPortableApplianceId(Integer portableApplianceId);
}
