/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.capeelectric.model.Cables;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface CablesRepository extends CrudRepository<Cables, Integer>{
	
	Optional<Cables> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<Cables> findByCableId(Integer cableId);
}
