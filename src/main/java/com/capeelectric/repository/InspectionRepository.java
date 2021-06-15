package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.IpaoInspection;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface InspectionRepository extends CrudRepository<IpaoInspection, Integer>{

	public Optional<IpaoInspection> findBySiteId(Integer siteId);

	public List<IpaoInspection> findByUserNameAndSiteId(String userName, Integer siteId);

}
