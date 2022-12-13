/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.ACB;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface ACBRepository extends CrudRepository<ACB, Integer>{
	
	Optional<ACB> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<ACB> findByAcbID(Integer acbID);

}