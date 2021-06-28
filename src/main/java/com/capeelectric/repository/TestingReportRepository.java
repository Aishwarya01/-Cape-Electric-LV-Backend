package com.capeelectric.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.TestingReport;

@Repository
public interface TestingReportRepository extends CrudRepository<TestingReport, Integer> {
	Optional<TestingReport> findBySiteId(Integer siteId);

	List<TestingReport> findByUserNameAndSiteId(String userName, Integer siteId);

}
