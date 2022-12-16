/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.DieselGenerator;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DieselGeneratorRepository extends CrudRepository<DieselGenerator, Integer>{
	
	Optional<DieselGenerator> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<DieselGenerator> findByDieselGeneratorID(Integer dieselGeneratorId);
}
