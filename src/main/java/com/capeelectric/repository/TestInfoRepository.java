package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Testing;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface TestInfoRepository extends CrudRepository<Testing, Integer> {

	Optional<Testing> findBySiteId(Integer siteId);

	List<Testing> findByUserNameAndSiteId(String userName, Integer siteId);
}
