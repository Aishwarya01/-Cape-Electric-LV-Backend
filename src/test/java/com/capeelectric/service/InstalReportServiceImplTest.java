package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.repository.InstalReportDetailsRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.InstalReportServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InstalReportServiceImplTest {

	@InjectMocks
	InstalReportServiceImpl instalReportServiceImpl;

	@MockBean
	private InstalReportDetailsRepository installationReportRepository;

	@MockBean
	private UserRepository userRepository;

	private ReportDetails reportDetails;

	@Test
	public void testAddInstallationReport() throws InstalReportException {
		reportDetails.setUserName("software@capeindia.com");

		when(installationReportRepository.save(reportDetails)).thenReturn(reportDetails);
		instalReportServiceImpl.addInstallationReport(reportDetails);

		InstalReportException exception = Assertions.assertThrows(InstalReportException.class,
				() -> instalReportServiceImpl.addInstallationReport(null));
		assertEquals(exception.getMessage(), "invalid inputs");

	}

	@Test
	public void testRetrieveInstallationReport() throws InstalReportException {
		reportDetails.setUserName("software@capeindia.com");

		ArrayList<ReportDetails> list = new ArrayList<ReportDetails>();
		list.add(reportDetails);
		when(installationReportRepository.findByUserName("software@capeindia.com")).thenReturn(list);
		instalReportServiceImpl.retrieveInstallationReport("software@capeindia.com");

		InstalReportException exception = Assertions.assertThrows(InstalReportException.class,
				() -> instalReportServiceImpl.retrieveInstallationReport(null));
		assertEquals(exception.getMessage(), "invalid inputs");
	}
}
