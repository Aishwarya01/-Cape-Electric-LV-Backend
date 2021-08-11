package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
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

	/**
	 * @ siteId unique for summary object
	 * @param Summary object
	 * addSummary method to find summary object based on input summary_siteId
	 * if not available summary object will be saved
	 * 
	*/
	@Override
	public void addSummary(Summary summary) throws SummaryException {
		if (summary.getUserName() != null && !summary.getUserName().isEmpty() && summary.getSiteId() != null && summary.getSiteId() != 0) {
			Optional<Summary> summaryRepo = summaryRepository.findBySiteId(summary.getSiteId());
			if (!summaryRepo.isPresent() || !summaryRepo.get().getSiteId().equals(summary.getSiteId())) {
				summary.setCreatedDate(LocalDateTime.now());
				summaryRepository.save(summary);
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
}
