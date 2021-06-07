package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.InstalReportException;
import com.capeelectric.model.ReportDetails;
import com.capeelectric.model.SignatorDetails;
import com.capeelectric.service.InstalReportService;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class InstallReportControllerTest {

	@InjectMocks
	private InstallReportController instalReportController;
	
	@MockBean
	private InstalReportService instalReportService;
	
	ReportDetails reportDetails;
		
	{
		Set<SignatorDetails> set = new HashSet<SignatorDetails>();
		
		reportDetails=new ReportDetails();
		reportDetails.setCompany("capeelectric");
		reportDetails.setCreatedBy("software@capeindia.com");
		reportDetails.setDescriptionPremise("");
		reportDetails.setDescriptionReport("IEC 60364 gives the rules for the design, erection, and verification of electrical installations up to 1000 V AC and 1500 V DC. These rules are adopted/followed worldwide as wiring rules. Some of the adopted national versions of this standard are BS7671 in UK, VDE0100 in Germany … etc. The NFPA 70 (NEC) of USA closely follows the fundamental safety measures recommended in IEC 60364.");
		reportDetails.setDesignation("chennai");
		reportDetails.setEstimatedWireAge("25");
		reportDetails.setEvidanceAddition("");
		reportDetails.setExtentInstallation("");
		reportDetails.setInstallationDetails("IEC 60364 gives the rules for the design, erection, and verification of electrical installations up to 1000 V AC and 1500 V DC. These rules are adopted/followed worldwide as wiring rules. Some of the adopted national versions of this standard are BS7671 in UK, VDE0100 in Germany … etc. The NFPA 70 (NEC) of USA closely follows the fundamental safety measures recommended in IEC 60364.");
		reportDetails.setInstallationType("new installation");
		reportDetails.setLastInspection("march 20th");
		reportDetails.setNextInspection("may 20th");
		reportDetails.setPreviousRecords("no");
		reportDetails.setReasonOfReport("IEC 60364 gives the rules for the design, erection, and verification of electrical installations up to 1000 V AC and 1500 V DC. These rules are adopted/followed worldwide as wiring rules. Some of the adopted national versions of this standard are BS7671 in UK, VDE0100 in Germany … etc. The NFPA 70 (NEC) of USA closely follows the fundamental safety measures recommended in IEC 60364.");
		reportDetails.setReportId(12);
		reportDetails.setSignatorDetails(set);
		reportDetails.setUserName("software@capeindia.com");
		reportDetails.setVerficationDate("20-03-2023");
		reportDetails.setVerifedEngineer("cape");
		reportDetails.setClientDetails(""); 
	}
	
	
	@Test
	public void testAddInstallationReport() throws InstalReportException {
		
		ResponseEntity<String> response = instalReportController.addInstallationReport(reportDetails);
		assertEquals(response.getBody(), "Report successfully saved");
		
	}
	
	@Test
	public void testRetrieveInstallationReport() throws InstalReportException {

		ResponseEntity<List<ReportDetails>> report = instalReportController.retrieveInstallationReport(reportDetails.getUserName());
		assertEquals(report.getStatusCode(), HttpStatus.OK);
	}
}
