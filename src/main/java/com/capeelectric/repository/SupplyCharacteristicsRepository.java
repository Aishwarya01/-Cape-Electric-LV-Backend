package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SupplyCharacteristics;

/**
 **
  * @author capeelectricsoftware
 *
 */
public interface SupplyCharacteristicsRepository extends CrudRepository<SupplyCharacteristics, Integer> {

	public List<SupplyCharacteristics> findByUserNameAndSiteId(String userName, Integer siteId);

	public Optional<SupplyCharacteristics> findBySiteId(Integer siteId);

}
