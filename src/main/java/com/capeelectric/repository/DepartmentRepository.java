package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	List<Department> findByClientName(String clientName);

	List<String> findByDepartmentName(String departmentName);

	Department findByClientNameAndDepartmentName(String clientName, String departmentName);
	
	List<Department>  findByUserNameAndClientName(String userName,String clientName);
}