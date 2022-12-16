/**
 * 
 */
package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.TransformerDetails;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface TransformerRepository extends CrudRepository<TransformerDetails, Integer> {
	
	Optional<TransformerDetails> findByFileNameAndNodeId(String fileName, String nodeId);
	
	Optional<TransformerDetails> findByTransformerDetailsId(Integer transformerDetailsId);

}
