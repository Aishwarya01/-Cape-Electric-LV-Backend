package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.TestingReport;
import com.capeelectric.model.TestingReportComment;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.TestingReportRepository;
import com.capeelectric.service.PeriodicTestingService;
import com.capeelectric.util.Constants;
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
	
	private TestingReportComment testingComment;
	
	private String viewerName;
	
	/**
	 * @param Testing
	 * addTestingReport method to Testing object will be storing corresponding tables
	*/
	@Override
	public void addTestingReport(TestingReport testingReport) throws PeriodicTestingException {
		List<TestingReportComment> listOfComments = new ArrayList<TestingReportComment>();
		if (testingReport.getUserName() != null && testingReport.getSiteId() != null) {

			Optional<TestingReport> testingRepo = testingReportRepository.findBySiteId(testingReport.getSiteId());
			if (!testingRepo.isPresent() || !testingRepo.get().getSiteId().equals(testingReport.getSiteId())) {
				testingComment = new TestingReportComment();
				testingComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
				testingComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
				testingComment.setNoOfComment(1);
				testingComment.setTestingReport(testingReport);
				listOfComments.add(testingComment);
				testingReport.setTestingComment(listOfComments);
				testingReport.setCreatedDate(LocalDateTime.now());
				testingReport.setCreatedBy(userFullName.findByUserName(testingReport.getUserName()));
				testingReport.setUpdatedDate(LocalDateTime.now());
				testingReport.setUpdatedBy(userFullName.findByUserName(testingReport.getUserName()));
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
			List<TestingReport> testingReportRepo = testingReportRepository.findByUserNameAndSiteId(userName, siteId);
			if (testingReportRepo != null) {
				for (TestingReport testingReport : testingReportRepo) {
					sortingDateTime(testingReport.getTestingComment());
				}
				return testingReportRepo;
			} else {
				throw new PeriodicTestingException("Given UserName & Site doesn't exist Testing");
			}
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
				testingReport.setUpdatedBy(userFullName.findByUserName(testingReport.getUserName()));
				testingReportRepository.save(testingReport);
			} else {
				throw new PeriodicTestingException("Given SiteId and ReportId is Invalid");
			}

		} else {
			throw new PeriodicTestingException("Invalid inputs");
		}
	}

	@Override
	public void sendComments(String userName, Integer siteId, TestingReportComment testingReportComment)
			throws PeriodicTestingException {

		TestingReport testingReport = verifyCommentsInfo(userName, siteId, testingReportComment, Constants.SEND_COMMENT);
		if (testingReport != null) {
			testingReportRepository.save(testingReport);
		} else {
			throw new PeriodicTestingException("Testing-Information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, TestingReportComment testingReportComment)
			throws PeriodicTestingException {
		TestingReport testingReport = verifyCommentsInfo(inspectorUserName, siteId, testingReportComment, Constants.REPLY_COMMENT);
		if (testingReport != null) {
			testingReportRepository.save(testingReport);
			return viewerName;
		} else {
			throw new PeriodicTestingException("Testing-Information doesn't exist for given Site-Id");
		}
	}
	
	@Override
	public void approveComments(String userName, Integer siteId, TestingReportComment testingReportComment)
			throws PeriodicTestingException {
		TestingReport testingReport = verifyCommentsInfo(userName, siteId, testingReportComment, Constants.APPROVE_REJECT_COMMENT);
		if (testingReport != null) {
			testingReportRepository.save(testingReport);
		} else {
			throw new PeriodicTestingException("Testing-Information doesn't exist for given Site-Id");
		}
	}
	
	private TestingReport verifyCommentsInfo(String userName, Integer siteId, TestingReportComment testingReportComment,
			String process) throws PeriodicTestingException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && testingReportComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)
					&& siteRepo.get().getAssignedTo() != null && siteRepo.get().getUserName() != null
					&& siteRepo.get().getSite() != null) {
				Optional<TestingReport> testingReportRepo = testingReportRepository.findBySiteId(siteId);
				if (testingReportRepo.isPresent() && testingReportRepo.get() != null
						&& checkInspectorViewer(userName, process, siteRepo, testingReportRepo)) {
					TestingReport testingReport = testingReportRepo.get();
					testingReport.setUpdatedDate(LocalDateTime.now());
					testingReport.setUpdatedBy(userName);
					List<TestingReportComment> testingReportCommentRepo = testingReport.getTestingComment();

					for (TestingReportComment testingReportCommentItr : testingReportCommentRepo) {
						if (testingReportCommentItr != null && testingReportCommentItr.getCommentsId() != null
								&& testingReportCommentItr.getCommentsId()
										.equals(testingReportComment.getCommentsId())) {
							flagInspectionComment = false;

							testingReportCommentItr.setTestingReport(testingReport);

							if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
								testingReportCommentItr.setSiteName(siteRepo.get().getSite());
								testingReportCommentItr.setInspectorEmail(siteRepo.get().getUserName());
								testingReportCommentItr.setViewerUserEmail(siteRepo.get().getAssignedTo());
								testingReportCommentItr.setViewerDate(LocalDateTime.now());
								testingReportCommentItr.setViewerComment(testingReportComment.getViewerComment());
								testingReportCommentItr.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
								testingReportCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								testingReportCommentRepo.add(testingReportCommentItr);
								testingReport.setTestingComment(testingReportCommentRepo);
								return testingReport;
							}
							if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
								testingReportCommentItr.setInspectorDate(LocalDateTime.now());
								testingReportCommentItr.setInspectorUserName(userFullName.findByUserName(userName));
								testingReportCommentItr.setInspectorComment(testingReportComment.getInspectorComment());
								testingReportCommentItr.setInspectorFlag(Constants.INCREASED_FLAG_VALUE);
								testingReportCommentRepo.add(testingReportCommentItr);
								testingReport.setTestingComment(testingReportCommentRepo);
								return testingReport;
							}
							if (process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
								testingReportCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								testingReportCommentItr.setApproveOrReject(testingReportComment.getApproveOrReject());
								testingReportCommentRepo.add(testingReportCommentItr);
								testingReport.setTestingComment(testingReportCommentRepo);
								return testingReport;
							}
						}
					}
					if (flagInspectionComment) {

						if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
							testingReportComment.setNoOfComment(checkNoOfComments(testingReport.getTestingComment()));
							testingReportComment.setTestingReport(testingReport);
							testingReportComment.setSiteName(siteRepo.get().getSite());
							testingReportComment.setInspectorEmail(siteRepo.get().getUserName());
							testingReportComment.setViewerUserEmail(siteRepo.get().getAssignedTo());
							testingReportComment.setViewerDate(LocalDateTime.now());
							testingReportComment.setViewerUserName(userFullName.findByUserName(userName));
							testingReportComment.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
							testingReportComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							testingReportCommentRepo.add(testingReportComment);
							testingReport.setTestingComment(testingReportCommentRepo);
							return testingReport;
						} else {
							throw new PeriodicTestingException("Sending viewer comments faild");
						}
					}
				} else {
					throw new PeriodicTestingException("Given username not have access for comments");
				}

			} else {
				throw new PeriodicTestingException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			throw new PeriodicTestingException("Invalid Inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<TestingReportComment> listOfComments) {
		Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
	}
	
	private Integer checkNoOfComments(List<TestingReportComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (TestingReportComment testingReportCommentItr : listOfComments) {
			if (testingReportCommentItr != null && testingReportCommentItr.getNoOfComment() != null
					&& maxNum <= testingReportCommentItr.getNoOfComment()) {
				maxNum = testingReportCommentItr.getNoOfComment();
				approveRejectedFlag = testingReportCommentItr.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
			return maxNum + 1;
		} else {
			return maxNum;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<TestingReport> testingReportRepo) throws PeriodicTestingException {
		Boolean flag = false;
		if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& testingReportRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(testingReportRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				throw new PeriodicTestingException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase(Constants.SEND_COMMENT) || process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					throw new PeriodicTestingException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
}
