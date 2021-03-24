package com.capeelectric.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.Company;
@Repository
public interface CompanyDetailsRepository extends CrudRepository< Company ,Integer> {

}
