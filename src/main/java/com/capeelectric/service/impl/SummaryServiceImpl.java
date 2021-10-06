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
import com.capeelectric.util.Constants;
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
	
	private List<SummaryComment> listOfComments;
	
	private Site site;
	
	private Optional<Site> siteRepo;
	
	private String viewerName;
	
	/**
	 * @ siteId unique for summary object
	 * @param Summary object
	 * addSummary method to find summary object based on input summary_siteId
	 * if not available summary object will be saved
	 * 
	*/
	@Override
	public void addSummary(Summary summary) throws SummaryException {
		listOfComments = new ArrayList<SummaryComment>();
		if (summary.getUserName() != null && !summary.getUserName().isEmpty() && summary.getSiteId() != null
				&& summary.getSiteId() != 0) {
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(summary.getSiteId());
			if (!summaryRepo.isPresent() || !summaryRepo.get().getSiteId().equals(summary.getSiteId())) {
				summaryComment = new SummaryComment();
				summaryComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
				summaryComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
				summaryComment.setNoOfComment(1);
				summaryComment.setSummary(summary);
				listOfComments.add(summaryComment);
				summary.setSummaryComment(listOfComments);
				summary.setCreatedDate(LocalDateTime.now());
				summary.setUpdatedDate(LocalDateTime.now());
				summary.setCreatedBy(userFullName.findByUserName(summary.getUserName()));
				summary.setUpdatedBy(userFullName.findByUserName(summary.getUserName()));
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
				summary.setUpdatedBy(userFullName.findByUserName(summary.getUserName()));
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
		Summary summary = verifyCommentsInfo(userName, siteId, summaryComment, Constants.SEND_COMMENT);
		if (summary != null) {
			summaryRepository.save(summary);
		} else {
			throw new SummaryException("Testing-Information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, SummaryComment summaryComment)
			throws SummaryException {
		Summary summary = verifyCommentsInfo(inspectorUserName, siteId, summaryComment, Constants.REPLY_COMMENT);
		if (summary != null) {
			summaryRepository.save(summary);
			return viewerName;
		} else {
			throw new SummaryException("Testing-Information doesn't exist for given Site-Id");
		}
	}
	
	@Override
	public void approveComments(String userName, Integer siteId, SummaryComment summaryComment)
			throws SummaryException {
		Summary summary = verifyCommentsInfo(userName, siteId, summaryComment, Constants.APPROVE_REJECT_COMMENT);
		if (summary != null) {
			summaryRepository.save(summary);
		} else {
			throw new SummaryException("Testing-Information doesn't exist for given Site-Id");
		}
	}

	private Summary verifyCommentsInfo(String userName, Integer siteId,
			SummaryComment summaryComment, String process) throws SummaryException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && summaryComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)
					&& siteRepo.get().getAssignedTo() != null && siteRepo.get().getUserName() != null
					&& siteRepo.get().getSite() != null) {
				Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);

				if (summaryRepo.isPresent() && summaryRepo.get() != null
						&& checkInspectorViewer(userName, process, siteRepo, summaryRepo)) {

					Summary summary = summaryRepo.get();
					summary.setUpdatedDate(LocalDateTime.now());
					summary.setUpdatedBy(userName);
					List<SummaryComment> summaryCommentRepo = summary.getSummaryComment();

					for (SummaryComment summaryCommentItr : summaryCommentRepo) {
						if (summaryCommentRepo != null && summaryCommentItr.getCommentsId() != null
								&& summaryCommentItr.getCommentsId().equals(summaryComment.getCommentsId())) {
							flagInspectionComment = false;

							summaryCommentItr.setSummary(summary);

							if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
								summaryCommentItr.setSiteName(siteRepo.get().getSite());
								summaryCommentItr.setInspectorEmail(siteRepo.get().getUserName());
								summaryCommentItr.setViewerUserEmail(siteRepo.get().getAssignedTo());
								summaryCommentItr.setViewerDate(LocalDateTime.now());
								summaryCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								summaryCommentItr.setViewerComment(summaryComment.getViewerComment());
								summaryCommentItr.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
								summaryCommentRepo.add(summaryCommentItr);
								summary.setSummaryComment(summaryCommentRepo);
								return summary;
							}
							if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
								summaryCommentItr.setInspectorDate(LocalDateTime.now());
								summaryCommentItr.setInspectorUserName(userFullName.findByUserName(userName));
								summaryCommentItr.setInspectorComment(summaryComment.getInspectorComment());
								summaryCommentItr.setInspectorFlag(Constants.INCREASED_FLAG_VALUE);
								summaryCommentRepo.add(summaryCommentItr);
								summary.setSummaryComment(summaryCommentRepo);
								return summary;
							}
							if (process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
								summaryCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								summaryCommentItr.setApproveOrReject(summaryComment.getApproveOrReject());
								summaryCommentRepo.add(summaryCommentItr);
								summary.setSummaryComment(summaryCommentRepo);
								return summary;
							}
						}
					}
					if (flagInspectionComment) {
						if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
							summaryComment.setNoOfComment(checkNoOfComments(summary.getSummaryComment()));
							summaryComment.setSummary(summary);
							summaryComment.setSiteName(siteRepo.get().getSite());
							summaryComment.setInspectorEmail(siteRepo.get().getUserName());
							summaryComment.setViewerUserEmail(siteRepo.get().getAssignedTo());
							summaryComment.setViewerDate(LocalDateTime.now());
							summaryComment.setViewerUserName(userFullName.findByUserName(userName));
							summaryComment.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
							summaryComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							summaryCommentRepo.add(summaryComment);
							summary.setSummaryComment(summaryCommentRepo);
							return summary;
						} else {
							throw new SummaryException("Sending viewer comments faild");
						}
					}
				} else {
					throw new SummaryException("Given username not have access for comments");
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
			if (SummaryCommentItr != null && SummaryCommentItr.getNoOfComment() != null
					&& maxNum <= SummaryCommentItr.getNoOfComment()) {
				maxNum = SummaryCommentItr.getNoOfComment();
				approveRejectedFlag = SummaryCommentItr.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
			return maxNum + 1;
		} else {
			return maxNum + 1;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<Summary> summaryRepo) throws SummaryException {
		Boolean flag = false;
		if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& summaryRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(summaryRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				throw new SummaryException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase(Constants.SEND_COMMENT) || process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					throw new SummaryException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
}
