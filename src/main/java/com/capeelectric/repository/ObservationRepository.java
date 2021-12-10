package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ObservationComponent;

@Repository
public interface ObservationRepository extends CrudRepository<ObservationComponent, Integer> {

	Optional<ObservationComponent> findByUserNameAndSiteId(String userName, Integer siteId);

	Optional<ObservationComponent> findBySiteId(Integer siteId);

}
