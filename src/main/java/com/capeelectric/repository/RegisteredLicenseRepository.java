package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.RegisteredLicense;


/**
 * 
 * @author capeelectricsoftware
 *
 */
@Repository
public interface RegisteredLicenseRepository extends CrudRepository<RegisteredLicense, Integer> {

}
