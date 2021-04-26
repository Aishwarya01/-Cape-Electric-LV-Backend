package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
	Optional<Company> findByClientName(String clientName);

	List<Company> findByUserName(String userName);
	
	Optional<Company> findByUserNameAndClientName(String userName,String clientName);
}
