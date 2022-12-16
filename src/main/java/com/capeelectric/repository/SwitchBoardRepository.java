/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SwitchBoard;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface SwitchBoardRepository extends CrudRepository<SwitchBoard, Integer> {
	
	Optional<SwitchBoard> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<SwitchBoard> findBySwitchBoardId(Integer switchBoardId);

}
