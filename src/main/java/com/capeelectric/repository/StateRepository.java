package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.State;

@Repository
public interface StateRepository extends CrudRepository<State, Integer> {
	@Query(value = "select * from State_Table where CountryID = (select id from Country_Table where name=?)", nativeQuery = true)
	List<State> fetchStatesByCountryName(String name);
}
