
package com.capeelectric.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capeelectric.model.ReportDetails;

@Repository
public interface InstalReportDetailsRepository extends CrudRepository<ReportDetails, Integer> {
	List<ReportDetails> findByUserName(String userName);
}
