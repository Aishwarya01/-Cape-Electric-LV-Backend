
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.repository.InstalReportDetailsRepository;
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
}
