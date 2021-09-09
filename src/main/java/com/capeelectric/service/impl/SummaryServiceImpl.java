package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
			return summaryRepository.findByUserNameAndSiteId(userName, siteId);
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
	public Summary sendComments(String userName, Integer siteId, String comments) throws SummaryException {
		if (userName != null && siteId != null && comments != null) {
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
			if (summaryRepo.isPresent() && summaryRepo.get().getUserName().equalsIgnoreCase(userName)) {
				Summary summary = summaryRepo.get();
				summary.setUpdatedDate(LocalDateTime.now());
				summary.setUpdatedBy(userName);
				summaryComment = new SummaryComment();
				summaryComment.setViewerDate(LocalDateTime.now());
				summaryComment.setViewerComment(comments);
				summaryComment.setSummary(summary);
				listOfComments.add(summaryComment);
				summary.setSummaryComment(listOfComments);
				return summaryRepository.save(summary);
			} else {
				throw new SummaryException("Given SiteId is Invalid");
			}
		} else {
			throw new SummaryException("Invalid inputs");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, SummaryComment summaryComment)
			throws SummaryException {
		if (inspectorUserName != null && siteId != null && summaryComment.getCommentsId() != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersons2 : sitePersons) {
					Optional<Summary> summaryRepo = summaryRepository.findBySiteId(siteId);
					if (summaryRepo.isPresent() && summaryRepo.get().getUserName()
							.equalsIgnoreCase(sitePersons2.getPersonInchargeEmail())) {
						Summary summary = summaryRepo.get();
						summary.setUpdatedDate(LocalDateTime.now());
						summary.setUpdatedBy(inspectorUserName);
						List<SummaryComment> summaryCommentRepo = summary.getSummaryComment();

						for (SummaryComment summaryCommentItr : summaryCommentRepo) {
							if (summaryCommentItr.getCommentsId().equals(summaryComment.getCommentsId())) {
								summaryCommentItr.setInspectorDate(LocalDateTime.now());
								summaryCommentItr.setSummary(summary);
								summaryCommentItr.setInspectorComment(summaryComment.getInspectorComment());
								summaryCommentRepo.add(summaryCommentItr);
								summary.setSummaryComment(summaryCommentRepo);
								summaryRepository.save(summary);
								return summary.getUserName();
							}
						}

					}
				}

			} else {
				throw new SummaryException("Invalid Site-Id");
			}

		} else {
			throw new SummaryException("Invalid inputs");
		}
		return null;
	}
}
