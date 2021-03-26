package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
	List<String>  findByClientName(String clientName);
}
