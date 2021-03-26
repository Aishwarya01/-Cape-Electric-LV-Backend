package com.capeelectric.repository;

 
import org.springframework.data.repository.CrudRepository;

 import com.capeelectric.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
}
