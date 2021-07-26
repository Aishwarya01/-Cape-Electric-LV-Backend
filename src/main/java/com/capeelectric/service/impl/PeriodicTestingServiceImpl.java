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
import com.capeelectric.util.UserFullName;

/**
 * This TestInfoServiceImpl service class doing save and retrieve operation based on Testing
 * @author capeelectricsoftware
 *
 */
@Service
public class PeriodicTestingServiceImpl implements PeriodicTestingService {

	@Autowired
	private TestingReportRepository testingReportRepository;
	
	@Autowired
	private UserFullName userFullName;

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
	
	/**
	 * @reportId,siteId must required
	 * @param TestingReport Object
	 * updatePeriodicTesting method to finding the given TestingReportId is available or not in DB,
	 * if available only allowed for updating 
	 * 
	*/
	@Override
	public void updatePeriodicTesting(TestingReport testingReport) throws PeriodicTestingException {
		if (testingReport != null && testingReport.getTestingReportId() != null && testingReport.getTestingReportId() != 0
				&& testingReport.getSiteId() != null && testingReport.getSiteId() != 0) {
			Optional<TestingReport> periodicInspectionRepo = testingReportRepository
					.findById(testingReport.getTestingReportId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(testingReport.getSiteId())) {
				testingReport.setUpdatedDate(LocalDateTime.now());
				testingReport.setUpdatedBy(userFullName.getFullName(testingReport.getUserName()));
				testingReportRepository.save(testingReport);
			} else {
				throw new PeriodicTestingException("Given SiteId and ReportId is Invalid");
			}

		} else {
			throw new PeriodicTestingException("Invalid inputs");
		}
		
	}

}
