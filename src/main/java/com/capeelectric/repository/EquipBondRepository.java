/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.EquipotentialBonding;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface EquipBondRepository extends CrudRepository<EquipotentialBonding, Integer>{
	
	Optional<EquipotentialBonding> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<EquipotentialBonding> findByEquipBondID(Integer equipBondID);

}