package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Testing;
/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface TestInfoRepository extends CrudRepository<Testing, Integer> {

	Optional<Testing> findByUserNameAndSiteId(String userName, Integer siteId);

}
