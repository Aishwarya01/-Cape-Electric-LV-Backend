
package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.User;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.InstalReportService;

@Service
public class InstalReportServiceImpl implements InstalReportService {

	@Autowired
	private InstalReportDetailsRepository installationReportRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addInstallationReport(ReportDetails reportDetails) throws InstalReportException {
		if (reportDetails != null && reportDetails.getUserName() != null) {
			reportDetails.setCreatedDate(LocalDateTime.now());
			reportDetails.setCreatedBy(generateFullName(reportDetails.getUserName()));
			installationReportRepository.save(reportDetails);

		} else {
			throw new InstalReportException("invalid inputs");
		}
	}

	@Override
	public List<ReportDetails> retrieveInstallationReport(String userName) throws InstalReportException {
		if (userName != null) {

			return installationReportRepository.findByUserName(userName);

		} else {
			throw new InstalReportException("invalid inputs");
		}
	}

	private String generateFullName(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent() && user.get() != null)
			return user.get().getFirstname() + " " + user.get().getLastname();
		return "";
	}
}