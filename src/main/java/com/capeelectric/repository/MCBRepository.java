/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.MCB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface MCBRepository extends CrudRepository<MCB, Integer>{
	
	Optional<MCB> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<MCB> findByMcbID(Integer mcbID);

}
