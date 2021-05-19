package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.SignatorDetails;
import com.capeelectric.model.User;
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
	
	private User user;

	private Optional<User> optionaluser;

	{
		user = new User();
		user.setUsername("lvsystem@capeindia.net");
		user.setPassword("cape");
		user.setEmail("lvsystem@capeindia.net");
		user.setUserexist(true);
		user.setActive(true);

		optionaluser = Optional.of(user);
	}
		
	{
		Set<SignatorDetails> set = new HashSet<SignatorDetails>();

		reportDetails = new ReportDetails();
		reportDetails.setCompany("capeelectric");
		reportDetails.setCreatedBy("software@capeindia.com");
		reportDetails.setDescriptionPremise("");
		reportDetails.setDescriptionReport(
				"IEC 60364 gives the rules for the design, erection, and verification of electrical installations up to 1000 V AC and 1500 V DC. These rules are adopted/followed worldwide as wiring rules. Some of the adopted national versions of this standard are BS7671 in UK, VDE0100 in Germany … etc. The NFPA 70 (NEC) of USA closely follows the fundamental safety measures recommended in IEC 60364.");
		reportDetails.setDesignation("chennai");
		reportDetails.setEstimatedWireAge("25");
		reportDetails.setEvidanceAddition("");
		reportDetails.setExtentInstallation("");
		reportDetails.setInstallationDetails(
				"IEC 60364 gives the rules for the design, erection, and verification of electrical installations up to 1000 V AC and 1500 V DC. These rules are adopted/followed worldwide as wiring rules. Some of the adopted national versions of this standard are BS7671 in UK, VDE0100 in Germany … etc. The NFPA 70 (NEC) of USA closely follows the fundamental safety measures recommended in IEC 60364.");
		reportDetails.setInstallationType("new installation");
		reportDetails.setLastInspection("march 20th");
		reportDetails.setNextInspection("may 20th");
		reportDetails.setPreviousRecords("no");
		reportDetails.setReasonOfReport(
				"IEC 60364 gives the rules for the design, erection, and verification of electrical installations up to 1000 V AC and 1500 V DC. These rules are adopted/followed worldwide as wiring rules. Some of the adopted national versions of this standard are BS7671 in UK, VDE0100 in Germany … etc. The NFPA 70 (NEC) of USA closely follows the fundamental safety measures recommended in IEC 60364.");
		reportDetails.setReportId(12);
		reportDetails.setSignatorDetails(set);
		reportDetails.setUserName("software@capeindia.com");
		reportDetails.setVerficationDate("20-03-2023");
		reportDetails.setVerifedEngineer("cape");
		reportDetails.setClientDetails("");
	}

	@Test
	public void testAddInstallationReport() throws InstalReportException {
		reportDetails.setUserName("software@capeindia.com");

		when(installationReportRepository.save(reportDetails)).thenReturn(reportDetails);
		instalReportServiceImpl.addInstallationReport(reportDetails);

		when(userRepository.findByUsername("software@capeindia.com")).thenReturn(optionaluser);
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
		List<ReportDetails> installationReport = instalReportServiceImpl
				.retrieveInstallationReport("software@capeindia.com");
		assertNotNull(installationReport);

		InstalReportException exception = Assertions.assertThrows(InstalReportException.class,
				() -> instalReportServiceImpl.retrieveInstallationReport(null));
		assertEquals(exception.getMessage(), "invalid inputs");
	}
}
