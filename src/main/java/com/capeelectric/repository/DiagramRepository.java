/**
 * 
 */
package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.DiagramComponent;
import com.capeelectric.model.DiagramSymbol;

/**
 * @author CAPE-SOFTWARE
 *
 */
public interface DiagramRepository extends CrudRepository<DiagramComponent, Integer>{
	Optional<DiagramComponent> findByUserNameAndFileName(String userName, String fileName);

	DiagramComponent findByUserNameAndDiagramId(String userName, Integer diagramId);
	
	List<DiagramComponent> findByUserName(String userName);
	


}
