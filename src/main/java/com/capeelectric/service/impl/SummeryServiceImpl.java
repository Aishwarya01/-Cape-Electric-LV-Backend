package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SummaryException;
import com.capeelectric.model.Summary;
import com.capeelectric.repository.SummaryRepository;
import com.capeelectric.service.SummeryService;

/**
 * This SummeryServiceImpl service class doing add and retrieve operation related to Summary_model(SummeryObervation,SummeryDeclaration)
 * @author capeelectricsoftware
 *
 */
@Service
public class SummeryServiceImpl implements SummeryService {

	@Autowired
	private SummaryRepository summaryRepository;

	/**
	 * @ siteId unique for summary object
	 * @param Summary object
	 * addSummary method to find summary object based on input summary_siteId
	 * if not available summary object will be saved
	 * 
	*/
	@Override
	public void addSummary(Summary summary) throws SummaryException {
		if (summary.getUserName() != null && summary.getSiteId() != null) {
			Optional<Summary> summaryRepo = summaryRepository.findByUserNameAndSiteId(summary.getUserName(),
					summary.getSiteId());
			if (!summaryRepo.isPresent() || !summaryRepo.get().getSiteId().equals(summary.getSiteId())) {
				summary.setCreatedDate(LocalDateTime.now());
				summaryRepository.save(summary);
			} else {
				throw new SummaryException("Given SiteId already present");
			}

		} else {
			throw new SummaryException("UserName and SiteId are Invalid Inputs");

		}

	}

	/**
	 * @param userName,siteId
	 * retrieveSummary method to retrieve the summary object based on userName and SiteId
	 * @return summary object
	*/
	@Override
	public Optional<Summary> retrieveSummary(String userName, Integer siteId) throws SummaryException {
		if (userName != null && siteId != null) {
			return summaryRepository.findByUserNameAndSiteId(userName, siteId);
		} else {
			throw new SummaryException("UserName and SiteId are Invalid Inputs");

		}
	}

}
