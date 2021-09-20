package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.Summary;
import com.capeelectric.model.SummaryComment;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.SummaryService;
import com.capeelectric.util.UserFullName;

/**
 * This SummaryServiceImpl service class doing add and retrieve operation related to Summary_model(SummaryObervation,SummaryDeclaration)
 * @author capeelectricsoftware
 *
 */
@Service
public class SummaryServiceImpl implements SummaryService {

	@Autowired
	private SummaryRepository summaryRepository;
	
	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SiteRepository siteRepository;
	
	private SummaryComment summaryComment;
	
	private List<SummaryComment> listOfComments = new ArrayList<SummaryComment>();
	
	private Site site;
	
	private Optional<Site> siteRepo;
	
	/**
	 * @ siteId unique for summary object
	 * @param Summary object
	 * addSummary method to find summary object based on input summary_siteId
	 * if not available summary object will be saved
	 * 
	*/
	@Override
	public void addSummary(Summary summary) throws SummaryException {
		if (summary.getUserName() != null && !summary.getUserName().isEmpty() && summary.getSiteId() != null
				&& summary.getSiteId() != 0) {
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(summary.getSiteId());
			if (!summaryRepo.isPresent() || !summaryRepo.get().getSiteId().equals(summary.getSiteId())) {
				summaryComment = new SummaryComment();
				summaryComment.setInspectorFlag("0");
				summaryComment.setViewerFlag("0");
				summaryComment.setNoOfComment(1);
				summaryComment.setSummary(summary);
				listOfComments.add(summaryComment);
				summary.setSummaryComment(listOfComments);
				summary.setCreatedDate(LocalDateTime.now());
				summary.setUpdatedDate(LocalDateTime.now());
				summary.setCreatedBy(userFullName.getFullName(summary.getUserName()));
				summary.setUpdatedBy(userFullName.getFullName(summary.getUserName()));
				summaryRepository.save(summary);
				siteRepo = siteRepository.findById(summary.getSiteId());
				if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(summary.getSiteId())) {
					site = siteRepo.get();
					site.setAllStepsCompleted("AllStepCompleted");
					siteRepository.save(site);
				} else {
					throw new SummaryException("Site-Id Information not Available in site_Table");
				}

			} else {
				throw new SummaryException("Site-Id Already Available");
			}

		} else {
			throw new SummaryException("Invalid Inputs");

		}

	}

	/**
	 * @param userName,siteId
	 * retrieveSummary method to retrieve the summary object based on userName and SiteId
	 * @return summary object
	*/
	@Override
	public List<Summary> retrieveSummary(String userName, Integer siteId) throws SummaryException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			List<Summary> summaryRepo = summaryRepository.findByUserNameAndSiteId(userName, siteId);
			if (summaryRepo != null) {
				for (Summary summary : summaryRepo) {
					sortingDateTime(summary.getSummaryComment());
				}
				return summaryRepo;
			} else {
				throw new SummaryException("Given UserName & Site doesn't exist Inspection");
			}
		} else {
			throw new SummaryException("Invalid Inputs");

		}
	}

	/**
	 * @reportId,siteId must required
	 * @param Summary Object
	 * updateSummary method to finding the given SummaryId is available or not in DB,
	 * if available only allowed for updating 
	 * 
	*/
	@Override
	public void updateSummary(Summary summary) throws SummaryException {

		if (summary != null && summary.getSummaryId() != null && summary.getSummaryId() != 0
				&& summary.getSiteId() != null && summary.getSiteId() != 0) {
			Optional<Summary> summaryRepo = summaryRepository.findById(summary.getSummaryId());
			if (summaryRepo.isPresent() && summaryRepo.get().getSiteId().equals(summary.getSiteId())) {
				summary.setUpdatedDate(LocalDateTime.now());
				summary.setUpdatedBy(userFullName.getFullName(summary.getUserName()));
				summaryRepository.save(summary);
			} else {
				throw new SummaryException("Given SiteId and ReportId is Invalid");
			}

		} else {
			throw new SummaryException("Invalid inputs");
		}
	}
	
	@Override
	public void sendComments(String userName, Integer siteId, SummaryComment summaryComment) throws SummaryException {
		Summary summary = verifyCommentsInfo(userName, siteId, summaryComment, "APPROVE");
		if (summary != null) {
			summaryRepository.save(summary);
		} else {
			throw new SummaryException("Testing-Information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, SummaryComment summaryComment)
			throws SummaryException {
		Summary summary = verifyCommentsInfo(inspectorUserName, siteId, summaryComment, "APPROVE");
		if (summary != null) {
			summaryRepository.save(summary);
			return summary.getUserName();
		} else {
			throw new SummaryException("Testing-Information doesn't exist for given Site-Id");
		}
	}
	
	@Override
	public void approveComments(String userName, Integer siteId, SummaryComment summaryComment)
			throws SummaryException {
		Summary summary = verifyCommentsInfo(userName, siteId, summaryComment, "APPROVE");
		if (summary != null) {
			summaryRepository.save(summary);
		} else {
			throw new SummaryException("Testing-Information doesn't exist for given Site-Id");
		}
	}

	private Summary verifyCommentsInfo(String userName, Integer siteId,
			SummaryComment summaryComment, String process) throws SummaryException {

		Boolean flagSitePersons = true;
		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && summaryComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersons2 : sitePersons) {
					Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
					if (summaryRepo.isPresent() && summaryRepo.get().getUserName()
							.equalsIgnoreCase(sitePersons2.getPersonInchargeEmail())) {
						flagSitePersons = false;
						Summary summary = summaryRepo.get();
						summary.setUpdatedDate(LocalDateTime.now());
						summary.setUpdatedBy(userName);
						List<SummaryComment> summaryCommentRepo = summary.getSummaryComment();

						for (SummaryComment summaryCommentItr : summaryCommentRepo) {
							if (summaryCommentItr.getCommentsId().equals(summaryComment.getCommentsId())) {
								flagInspectionComment = false;

								summaryCommentItr.setSummary(summary);

								if (process.equalsIgnoreCase("SEND")) {
									summaryCommentItr.setViewerDate(LocalDateTime.now());
									summaryCommentItr.setViewerComment(summaryComment.getViewerComment());
									summaryCommentItr.setViewerFlag("1");
									summaryCommentRepo.add(summaryCommentItr);
									summary.setSummaryComment(summaryCommentRepo);
									return summary;
								}
								if (process.equalsIgnoreCase("REPLY")) {
									summaryCommentItr.setInspectorDate(LocalDateTime.now());
									summaryCommentItr.setInspectorComment(summaryComment.getInspectorComment());
									summaryCommentItr.setInspectorFlag("1");
									summaryCommentRepo.add(summaryCommentItr);
									summary.setSummaryComment(summaryCommentRepo);
									return summary;
								}
								if (process.equalsIgnoreCase("APPROVE")) {
									summaryCommentItr.setViewerDate(LocalDateTime.now());
									summaryCommentItr.setApproveOrReject(summaryComment.getApproveOrReject());
									summaryCommentRepo.add(summaryCommentItr);
									summary.setSummaryComment(summaryCommentRepo);
									return summary;
								}
							}
						}
						if (flagInspectionComment) {
							if (process.equalsIgnoreCase("SEND")) {
								summaryComment.setNoOfComment(checkNoOfComments(summary.getSummaryComment()));
								summaryComment.setSummary(summary);
								summaryComment.setViewerDate(LocalDateTime.now());
								summaryComment.setViewerFlag("1");
								summaryComment.setInspectorFlag("0");
								summaryCommentRepo.add(summaryComment);
								summary.setSummaryComment(summaryCommentRepo);
								return summary;
							} else {
								throw new SummaryException("Sending viewer comments faild");
							}
						}
					}
				}
				if (flagSitePersons) {
					throw new SummaryException("PersonIncharge mail-Id not matched Given UserName");
				}

			} else {
				throw new SummaryException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			throw new SummaryException("Invalid inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<SummaryComment> listOfComments) {
		Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
	}
	
	private Integer checkNoOfComments(List<SummaryComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (SummaryComment SummaryCommentItr : listOfComments) {
			if (SummaryCommentItr != null && maxNum <= SummaryCommentItr.getNoOfComment()) {
				maxNum = SummaryCommentItr.getNoOfComment();
				approveRejectedFlag = SummaryCommentItr.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase("APPROVED")) {
			return maxNum + 1;
		} else {
			return maxNum + 1;
		}
	}
}
