/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Fan;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface FanRepository extends CrudRepository<Fan, Integer>{
	
	Optional<Fan> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<Fan> findByFanId(Integer fanId);

}