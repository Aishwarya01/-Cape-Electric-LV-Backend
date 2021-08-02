package com.capeelectric.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Admin;

public interface AdminControllRepositary  extends CrudRepository<Admin, Integer>{

	Optional<Admin> findByAdminname(String Adminname);

}
