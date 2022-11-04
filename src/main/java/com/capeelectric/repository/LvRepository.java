package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.licence.LvRegister;

public interface LvRepository extends CrudRepository<LvRegister, Integer> {

	public Optional<LvRegister> findByUserName(String userName);

}
