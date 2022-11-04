package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.licence.LpsRegister;

public interface LpsRegisterRepository extends CrudRepository<LpsRegister, Integer> {

	public Optional<LpsRegister> findByUsername(String userName);

}
