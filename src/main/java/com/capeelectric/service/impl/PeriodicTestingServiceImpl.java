package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.TestingReport;
import com.capeelectric.repository.SiteRepository;
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

	@Autowired
	private SiteRepository siteRepository;
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
				testingReport.setCreatedBy(userFullName.getFullName(testingReport.getUserName()));
				testingReport.setUpdatedDate(LocalDateTime.now());
				testingReport.setUpdatedBy(userFullName.getFullName(testingReport.getUserName()));
				testingReportRepository.save(testingReport);
			} else {
				throw new PeriodicTestingException("Site-Id Already Present");
			}
		} else {
			throw new PeriodicTestingException("Invalid Inputs");
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
			throw new PeriodicTestingException("Invalid Inputs");
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
		if (testingReport != null && testingReport.getTestingReportId() != null
				&& testingReport.getTestingReportId() != 0 && testingReport.getSiteId() != null
				&& testingReport.getSiteId() != 0) {
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

	@Override
	public TestingReport sendComments(String userName, Integer siteId, String comments)
			throws PeriodicTestingException {
		if (userName != null && siteId != null && comments != null) {
			Optional<TestingReport> periodicInspectionRepo = testingReportRepository.findBySiteId(siteId);
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getUserName().equalsIgnoreCase(userName)) {
				TestingReport testingReport = periodicInspectionRepo.get();
				testingReport.setUpdatedDate(LocalDateTime.now());
				testingReport.setUpdatedBy(userName);
				testingReport.setViewerComment(comments);
				return testingReportRepository.save(testingReport);
			} else {
				throw new PeriodicTestingException("Given SiteId is Invalid");
			}
		} else {
			throw new PeriodicTestingException("Invalid inputs");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, String comments)
			throws PeriodicTestingException {
		if (inspectorUserName != null && siteId != null && comments != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersons2 : sitePersons) {
					Optional<TestingReport> testingReportRepo = testingReportRepository.findBySiteId(siteId);
					if (testingReportRepo.isPresent() && testingReportRepo.get().getUserName()
							.equalsIgnoreCase(sitePersons2.getPersonInchargeEmail())) {
						TestingReport testingReport = testingReportRepo.get();
						testingReport.setUpdatedDate(LocalDateTime.now());
						testingReport.setUpdatedBy(inspectorUserName);
						testingReport.setInspectorComment(comments);
						testingReportRepository.save(testingReport);
						return testingReport.getUserName();
					} else {
						throw new PeriodicTestingException("Given SiteId is Invalid");
					}
				}

			} else {
				throw new PeriodicTestingException("Invalid Site-Id");
			}

		} else {
			throw new PeriodicTestingException("Invalid inputs");
		}
		return null;
	}
	
	
}
