package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.PeriodicTestingService;

/**
 * This TestInfoServiceImpl service class doing save and retrieve operation based on Testing
 * @author capeelectricsoftware
 *
 */
@Service
public class PeriodicTestingServiceImpl implements PeriodicTestingService {

	@Autowired
	private TestingReportRepository testingReportRepository;

	/**
	 * @param Testing
	 * addTestingReport method to Testing object will be storing corresponding tables
	*/
	@Override
	public void addTestingReport(TestingReport testingReport) throws PeriodicTestingException {
		if (testingReport.getUserName() != null && testingReport.getSiteId() != null) {

			Optional<TestingReport> testingRepo = testingReportRepository.findBySiteId(testingReport.getSiteId());
			if (!testingRepo.isPresent() || !testingRepo.get().getSiteId().equals(testingReport.getSiteId())) {
				testingReport.setCreatedDate(LocalDateTime.now());
				testingReportRepository.save(testingReport);
			} else {
				throw new PeriodicTestingException("SiteId Already Present");
			}
		} else {
			throw new PeriodicTestingException("UserName and SiteId Invalid Input");
		}
	}

	/**
	 * @param userName,siteId
	 * retrieveTestingReport method to retrieve based on userName and siteId
	 * @return Optional<Testing>
	 */
	@Override
	public List<TestingReport> retrieveTestingReport(String userName, Integer siteId) throws PeriodicTestingException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			return testingReportRepository.findByUserNameAndSiteId(userName, siteId);
		} else {
			throw new PeriodicTestingException("UserName and SiteId Invalid Input");
		}
	}

}
