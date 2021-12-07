package com.capeelectric.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.BoundingLocationReport;
import com.capeelectric.model.EarthingLocationReport;
import com.capeelectric.model.InstalLocationReport;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.SignatorDetails;
import com.capeelectric.model.SummaryObervation;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingRecords;

/**
 * This FindNonRemovedObject Util class finding non Removed object for all_steps
 * 
 * @author capeelectricsoftware
 *
 */
@Configuration
public class FindNonRemovedObject {

	public List<IpaoInspection> findNonRemovedInspectionLocation(PeriodicInspection inspectionRepo) {

		ArrayList<IpaoInspection> inspectionReport = new ArrayList<IpaoInspection>();
		List<IpaoInspection> findNonRemoveLocation = inspectionRepo.getIpaoInspection();
		for (IpaoInspection inspectionLocationReport : findNonRemoveLocation) {
			if (!inspectionLocationReport.getInspectionFlag().equalsIgnoreCase("R")) {
				inspectionReport.add(inspectionLocationReport);
			}
		}
		return inspectionReport;
	}

	public List<InstalLocationReport> findNonRemovedInstallLocation(SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<InstalLocationReport> locationReport = new ArrayList<InstalLocationReport>();
		List<InstalLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getInstalLocationReport();
		for (InstalLocationReport instalLocationReport : findNonRemoveLocation) {
			if (!instalLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				locationReport.add(instalLocationReport);
			}
		}
		return locationReport;
	}

	public List<BoundingLocationReport> findNonRemovedBondingLocation(
			SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<BoundingLocationReport> locationReport = new ArrayList<BoundingLocationReport>();
		List<BoundingLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getBoundingLocationReport();
		for (BoundingLocationReport bondingLocationReport : findNonRemoveLocation) {
			if (!bondingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				locationReport.add(bondingLocationReport);
			}
		}
		return locationReport;
	}

	public List<EarthingLocationReport> findNonRemovedEarthingLocation(
			SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<EarthingLocationReport> locationReport = new ArrayList<EarthingLocationReport>();
		List<EarthingLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getEarthingLocationReport();
		for (EarthingLocationReport earthingLocationReport : findNonRemoveLocation) {
			if (!earthingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				locationReport.add(earthingLocationReport);
			}
		}
		return locationReport;
	}

	public List<Testing> findNonRemoveTesting(List<Testing> listOfTesting) {
		for (Testing testing : listOfTesting) {
			if (testing != null && testing.getTestingStatus().equalsIgnoreCase("R")) {
				listOfTesting.remove(testing);
			} else {
				testing.setTestingRecords(findNonRemoveTestingRecord(testing.getTestingRecords()));
			}
		}
		return listOfTesting;
	}

	public List<TestingRecords> findNonRemoveTestingRecord(List<TestingRecords> listOfTestingRecords) {
		List<TestingRecords> listNonRemovedTestingRecord = new ArrayList<TestingRecords>();
		for (TestingRecords testingRecords : listOfTestingRecords) {
			if (testingRecords.getTestingRecordStatus().equalsIgnoreCase("R")) {
				listNonRemovedTestingRecord.add(testingRecords);
			}
		}
		return listNonRemovedTestingRecord;
	}

	public Set<SignatorDetails> findNonRemovedReport(Set<SignatorDetails> signatorDetails) {
		Set<SignatorDetails> signatorDetail = new HashSet<SignatorDetails>();
		for (SignatorDetails signatorDetailItr : signatorDetails) {
			if (signatorDetailItr != null && signatorDetailItr.getSignatorStatus() != null
					&& !signatorDetailItr.getSignatorStatus().equalsIgnoreCase("R")) {
				signatorDetail.add(signatorDetailItr);
			}
		}
		return signatorDetail;
	}

	public List<SummaryObervation> findNonRemoveObservation(List<SummaryObervation> summaryObervation) {
		List<SummaryObervation> obervationList = new ArrayList<SummaryObervation>();
		for (SummaryObervation obervation : summaryObervation) {
			if (obervation != null && obervation.getObervationStatus() != null
					&& !obervation.getObervationStatus().equalsIgnoreCase("R")) {
				obervationList.add(obervation);
			}
		}
		return obervationList;
	}

}
