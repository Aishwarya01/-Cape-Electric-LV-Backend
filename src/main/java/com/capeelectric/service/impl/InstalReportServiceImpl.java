
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.ReportDetailsComment;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.service.InstalReportService;
import com.capeelectric.util.UserFullName;

/**
 * This InstalReportServiceImpl service class doing save and retrieve operation related to ReportDetails
 * @author capeelectricsoftware
 *
 */
@Service
public class InstalReportServiceImpl implements InstalReportService {

	@Autowired
	private InstalReportDetailsRepository installationReportRepository;

	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SiteRepository siteRepository;
	
	private ReportDetailsComment reportDetailsComment;
	
	private String viewerName;
	
	/**
	 * @param ReportDetails
	 * addInstallationReport method to will be save ReportDetails object
	 * 
	*/
	@Override
	public void addInstallationReport(ReportDetails reportDetails) throws InstalReportException {
		List<ReportDetailsComment> listOfComments = new ArrayList<ReportDetailsComment>();
		
		if (reportDetails != null && reportDetails.getUserName() != null && reportDetails.getSiteId() != null) {
			Optional<ReportDetails> reportDetailsRepo = installationReportRepository
					.findBySiteId(reportDetails.getSiteId());
			if (!reportDetailsRepo.isPresent()
					|| !reportDetailsRepo.get().getSiteId().equals(reportDetails.getSiteId())) {
				reportDetailsComment = new ReportDetailsComment();
				reportDetailsComment.setInspectorFlag("0");
				reportDetailsComment.setViewerFlag("0");
				reportDetailsComment.setNoOfComment(1);
				reportDetailsComment.setReportDetails(reportDetails);
				listOfComments.add(reportDetailsComment);
				reportDetails.setReportDetailsComment(listOfComments);
				reportDetails.setCreatedDate(LocalDateTime.now());
				reportDetails.setUpdatedDate(LocalDateTime.now());
				reportDetails.setCreatedBy(userFullName.getFullName(reportDetails.getUserName()));
				reportDetails.setUpdatedBy(userFullName.getFullName(reportDetails.getUserName()));
				installationReportRepository.save(reportDetails);
			} else {
				throw new InstalReportException("Site-Id Details Already Available,Create New Site-Id");
			}

		} else {
			throw new InstalReportException("Invalid Inputs");
		}
	}

	/**
	 * @param userName
	 * retrieveInstallationReport method to will be save retrieve object based on userName
	 * @throws InspectionException 
	 * 
	*/
	@Override
	public List<ReportDetails> retrieveInstallationReport(String userName, Integer siteId)
			throws InstalReportException, InspectionException {
		if (userName != null) {
			List<ReportDetails> reportDetailsRepo = installationReportRepository.findByUserNameAndSiteId(userName,
					siteId);
			if (reportDetailsRepo != null) {
				for (ReportDetails reportDetails : reportDetailsRepo) {
					sortingDateTime(reportDetails.getReportDetailsComment());
				}
				return reportDetailsRepo;
			} else {
				throw new InspectionException("Given UserName & Site doesn't exist Basic-information");
			}
		} else {
			throw new InstalReportException("invalid inputs");
		}
	}

	
	/**
	 * @reportId,siteId must required
	 * @param ReportDetails Object
	 * updateInstallationReport method to finding the given reportId is available or not in DB,
	 * if available only allowed for updating 
	 * 
	*/
	@Override
	public void updateInstallationReport(ReportDetails reportDetails) throws InstalReportException {

		if (reportDetails != null && reportDetails.getReportId() != null && reportDetails.getReportId() != 0
				&& reportDetails.getSiteId() != null && reportDetails.getSiteId() != 0) {
			Optional<ReportDetails> reportDetailsRepo = installationReportRepository
					.findById(reportDetails.getReportId());
			if (reportDetailsRepo.isPresent()
					&& reportDetailsRepo.get().getSiteId().equals(reportDetails.getSiteId())) {
				reportDetails.setUpdatedDate(LocalDateTime.now());
				reportDetails.setUpdatedBy(userFullName.getFullName(reportDetails.getUserName()));
				installationReportRepository.save(reportDetails);
			} else {
				throw new InstalReportException("Given SiteId and ReportId is Invalid");
			}

		} else {
			throw new InstalReportException("Invalid inputs");
		}
	}

	@Override
	public void sendComments(String userName, Integer siteId, ReportDetailsComment reportDetailsComment) throws InstalReportException {
		ReportDetails reportDetails = verifyCommentsInfo(userName, siteId, reportDetailsComment, "SEND");
		if (reportDetails != null) {
			installationReportRepository.save(reportDetails);
		} else {
			throw new InstalReportException("Basic-Information information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, ReportDetailsComment reportDetailsComment)
			throws InstalReportException {
		ReportDetails reportDetails = verifyCommentsInfo(inspectorUserName, siteId, reportDetailsComment, "REPLY");
		if (reportDetails != null && viewerName != null) {
			installationReportRepository.save(reportDetails);
			return viewerName;
		} else {
			throw new InstalReportException("Basic-Information information doesn't exist for given Site-Id");
		}

	}
	
	@Override
	public void approveComments(String userName, Integer siteId, ReportDetailsComment reportDetailsComment)
			throws InstalReportException {
		ReportDetails reportDetails = verifyCommentsInfo(userName, siteId, reportDetailsComment, "APPROVE");
		if (reportDetails != null) {
			installationReportRepository.save(reportDetails);
		} else {
			throw new InstalReportException("Basic-Information doesn't exist for given Site-Id");
		}
	}
	
	private ReportDetails verifyCommentsInfo(String userName, Integer siteId,
			ReportDetailsComment reportDetailsComment, String process) throws InstalReportException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && reportDetailsComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Optional<ReportDetails> reportDetailsRepo = installationReportRepository.findBySiteId(siteId);
				if (reportDetailsRepo.isPresent() && reportDetailsRepo.get() != null
						&& reportDetailsRepo.get().getUserName() != null
						&& checkInspectorViewer(userName, process, siteRepo, reportDetailsRepo)) {

					ReportDetails reportDetails = reportDetailsRepo.get();
					reportDetails.setUpdatedDate(LocalDateTime.now());
					reportDetails.setUpdatedBy(userName);
					List<ReportDetailsComment> reportDetailsCommentRepo = reportDetails.getReportDetailsComment();

					for (ReportDetailsComment reportDetailsCommentItr : reportDetailsCommentRepo) {
						if (reportDetailsCommentItr.getCommentsId().equals(reportDetailsComment.getCommentsId())) {
							flagInspectionComment = false;

							reportDetailsCommentItr.setReportDetails(reportDetails);

							if (process.equalsIgnoreCase("SEND")) {
								reportDetailsCommentItr.setViewerDate(LocalDateTime.now());
								reportDetailsCommentItr.setViewerComment(reportDetailsComment.getViewerComment());
								reportDetailsCommentItr.setViewerFlag("1");
								reportDetailsCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								reportDetailsCommentRepo.add(reportDetailsCommentItr);
								reportDetails.setReportDetailsComment(reportDetailsCommentRepo);
								return reportDetails;
							}
							if (process.equalsIgnoreCase("REPLY")) {
								reportDetailsCommentItr.setInspectorDate(LocalDateTime.now());
								reportDetailsCommentItr.setInspectorComment(reportDetailsComment.getInspectorComment());
								reportDetailsCommentItr.setInspectorFlag("1");
								reportDetailsCommentItr.setInspectorUserName(userFullName.findByUserName(userName));
								reportDetailsCommentRepo.add(reportDetailsCommentItr);
								reportDetails.setReportDetailsComment(reportDetailsCommentRepo);
								return reportDetails;
							}
							if (process.equalsIgnoreCase("APPROVE")) {
								reportDetailsCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								reportDetailsCommentItr.setViewerDate(LocalDateTime.now());
								reportDetailsCommentItr.setApproveOrReject(reportDetailsComment.getApproveOrReject());
								reportDetailsCommentRepo.add(reportDetailsCommentItr);
								reportDetails.setReportDetailsComment(reportDetailsCommentRepo);
								return reportDetails;
							}
						}
					}
					if (flagInspectionComment) {
						if (process.equalsIgnoreCase("SEND")) {
							
							reportDetailsComment
									.setNoOfComment(checkNoOfComments(reportDetails.getReportDetailsComment()));
							reportDetailsComment.setReportDetails(reportDetails);
							reportDetailsComment.setViewerDate(LocalDateTime.now());
							reportDetailsComment.setViewerFlag("1");
							reportDetailsComment.setInspectorFlag("0");
							reportDetailsComment.setViewerUserName(userFullName.findByUserName(userName));
							reportDetailsCommentRepo.add(reportDetailsComment);
							reportDetails.setReportDetailsComment(reportDetailsCommentRepo);
							return reportDetails;
						} else {
							throw new InstalReportException("Sending viewer comments faild");
						}
					}
				}
				else {
					throw new InstalReportException("Given username not have access for comments");
				}

			} else {
				throw new InstalReportException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			throw new InstalReportException("Invalid inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<ReportDetailsComment> listOfComments) {
		Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
	}
	
	private Integer checkNoOfComments(List<ReportDetailsComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (ReportDetailsComment reportDetailsCommentItr : listOfComments) {
			if (reportDetailsCommentItr != null && maxNum <= reportDetailsCommentItr.getNoOfComment()) {
				maxNum = reportDetailsCommentItr.getNoOfComment();
				approveRejectedFlag = reportDetailsCommentItr.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase("APPROVED")) {
			return maxNum + 1;
		} else {
			return maxNum;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<ReportDetails> reportDetailsRepo) throws InstalReportException {
		Boolean flag = false;
		if (process.equalsIgnoreCase("REPLY")) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& reportDetailsRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(reportDetailsRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				throw new InstalReportException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase("SEND") || process.equalsIgnoreCase("APPROVE")) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					throw new InstalReportException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
}
