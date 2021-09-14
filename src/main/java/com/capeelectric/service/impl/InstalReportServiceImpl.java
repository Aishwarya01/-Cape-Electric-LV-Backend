
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private Set<ReportDetailsComment> listOfComments = new HashSet<ReportDetailsComment>();
	
	/**
	 * @param ReportDetails
	 * addInstallationReport method to will be save ReportDetails object
	 * 
	*/
	@Override
	public void addInstallationReport(ReportDetails reportDetails) throws InstalReportException {
		if (reportDetails != null && reportDetails.getUserName() != null && reportDetails.getSiteId() != null) {
			Optional<ReportDetails> reportDetailsRepo = installationReportRepository
					.findBySiteId(reportDetails.getSiteId());
			if (!reportDetailsRepo.isPresent()
					|| !reportDetailsRepo.get().getSiteId().equals(reportDetails.getSiteId())) {
				reportDetailsComment = new ReportDetailsComment();
				reportDetailsComment.setInspectorFlag("0");
				reportDetailsComment.setViewerFlag("0");
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
	 * 
	*/
	@Override
	public List<ReportDetails> retrieveInstallationReport(String userName,Integer siteId) throws InstalReportException {
		if (userName != null) {

			return installationReportRepository.findByUserNameAndSiteId(userName,siteId);

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
	public ReportDetails sendComments(String userName, Integer siteId, ReportDetailsComment reportDetailsComment) throws InstalReportException {
		if (userName != null && siteId != null && reportDetailsComment != null) {
			Optional<ReportDetails> reportDetailsRepo = installationReportRepository.findBySiteId(siteId);
			if (reportDetailsRepo.isPresent() && reportDetailsRepo.get().getUserName().equalsIgnoreCase(userName)) {
				ReportDetails reportDetails = reportDetailsRepo.get();
				reportDetails.setUpdatedDate(LocalDateTime.now());
				reportDetails.setUpdatedBy(userName);
				reportDetailsComment.setViewerFlag("1");
				reportDetailsComment.setViewerDate(LocalDateTime.now());
				reportDetailsComment.setReportDetails(reportDetails);
				listOfComments.add(reportDetailsComment);
				reportDetails.setReportDetailsComment(listOfComments);
				return installationReportRepository.save(reportDetails);
			} else {
				throw new InstalReportException("Given SiteId is Invalid");
			}
		} else {
			throw new InstalReportException("Invalid inputs");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, ReportDetailsComment reportDetailsComment)
			throws InstalReportException {
		if (inspectorUserName != null && siteId != null && reportDetailsComment.getCommentsId() != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersons2 : sitePersons) {
					Optional<ReportDetails> reportDetailsRepo = installationReportRepository.findBySiteId(siteId);
					if (reportDetailsRepo.isPresent() && reportDetailsRepo.get().getUserName()
							.equalsIgnoreCase(sitePersons2.getPersonInchargeEmail())) {
						ReportDetails reportDetails = reportDetailsRepo.get();
						reportDetails.setUpdatedDate(LocalDateTime.now());
						reportDetails.setUpdatedBy(inspectorUserName);
						Set<ReportDetailsComment> reportDetailsCommentRepo = reportDetails.getReportDetailsComment();

						for (ReportDetailsComment reportDetailsCommentItr : reportDetailsCommentRepo) {
							if (reportDetailsCommentItr.getCommentsId().equals(reportDetailsComment.getCommentsId())) {
								reportDetailsCommentItr.setInspectorDate(LocalDateTime.now());
								reportDetailsCommentItr.setReportDetails(reportDetails);
								reportDetailsCommentItr.setInspectorComment(reportDetailsComment.getInspectorComment());
								reportDetailsCommentItr.setInspectorFlag("1");
								reportDetailsCommentRepo.add(reportDetailsCommentItr);
								reportDetails.setReportDetailsComment(reportDetailsCommentRepo);
								installationReportRepository.save(reportDetails);
								return reportDetails.getUserName();
							}
						}

					}
				}

			} else {
				throw new InstalReportException("Invalid Site-Id");
			}

		} else {
			throw new InstalReportException("Invalid inputs");
		}
		return null;
	}
}
