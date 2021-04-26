package com.capeelectric.repository;

 
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.capeelectric.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
	Site findByClientNameAndDepartmentNameAndSite(String clientName, String departmentName,String site);
	
	List<Site> findByClientNameAndDepartmentName(String clientName, String departmentName);
}
