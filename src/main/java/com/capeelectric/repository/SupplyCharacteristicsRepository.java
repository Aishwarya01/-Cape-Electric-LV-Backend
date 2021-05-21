package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.SupplyCharacteristics;

public interface SupplyCharacteristicsRepository extends CrudRepository<SupplyCharacteristics, Integer> {

	public List<SupplyCharacteristics> findByUserNameAndSiteId(String userName, Integer siteId);

}
