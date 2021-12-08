package com.capeelectric.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Configuration;

import com.capeelectric.model.BoundingLocationReport;
import com.capeelectric.model.CircuitBreaker;
import com.capeelectric.model.EarthingLocationReport;
import com.capeelectric.model.InstalLocationReport;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.SignatorDetails;
import com.capeelectric.model.SummaryObervation;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.model.Testing;
import com.capeelectric.model.TestingEquipment;
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
			if (inspectionLocationReport.getInspectionFlag()==null || !inspectionLocationReport.getInspectionFlag().equalsIgnoreCase("R")) {
				if(inspectionLocationReport.getInspectionFlag()==null) {
					inspectionLocationReport.setInspectionFlag("N");
				}
				inspectionReport.add(inspectionLocationReport);
			}
		}
		return inspectionReport;
	}

	public List<InstalLocationReport> findNonRemovedInstallLocation(SupplyCharacteristics supplyCharacteristicsRepo) {
		ArrayList<InstalLocationReport> locationReport = new ArrayList<InstalLocationReport>();
		List<InstalLocationReport> findNonRemoveLocation = supplyCharacteristicsRepo.getInstalLocationReport();
		for (InstalLocationReport instalLocationReport : findNonRemoveLocation) {
			if (instalLocationReport.getInstalLocationReportStatus()==null || !instalLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				if(instalLocationReport.getInstalLocationReportStatus()==null) {
					instalLocationReport.setInstalLocationReportStatus("N");
				}
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
			if (bondingLocationReport.getInstalLocationReportStatus()==null || !bondingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				if(bondingLocationReport.getInstalLocationReportStatus()==null) {
					bondingLocationReport.setInstalLocationReportStatus("N");
				}
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
			if (earthingLocationReport.getInstalLocationReportStatus() ==null || !earthingLocationReport.getInstalLocationReportStatus().equalsIgnoreCase("R")) {
				if(earthingLocationReport.getInstalLocationReportStatus()==null) {
					earthingLocationReport.setInstalLocationReportStatus("N");
				}
				locationReport.add(earthingLocationReport);
			}
		}
		return locationReport;
	}

	public List<Testing> findNonRemoveTesting(List<Testing> listOfTesting) {
		for (Testing testing : listOfTesting) {
			if (testing != null && testing.getTestingStatus() !=null && testing.getTestingStatus().equalsIgnoreCase("R")) {
				listOfTesting.remove(testing);
			} else {
				if (testing.getTestingStatus() == null) {
					testing.setTestingStatus("N");
				}
				testing.setTestingRecords(findNonRemoveTestingRecord(testing.getTestingRecords()));
				testing.setTestingEquipment(findNonRemoveTestingEquipment(testing.getTestingEquipment()));
			}
		}
		return listOfTesting;
	}

	private List<TestingEquipment> findNonRemoveTestingEquipment(List<TestingEquipment> testingEquipment) {
		List<TestingEquipment> listNonRemovedTestingEquipment = new ArrayList<TestingEquipment>();
		for (TestingEquipment testingEquipmentItr : testingEquipment) {
			if (testingEquipmentItr.getTestingEquipmentStatus() == null || !testingEquipmentItr.getTestingEquipmentStatus().equalsIgnoreCase("R")) {
				if(testingEquipmentItr.getTestingEquipmentStatus() ==null) {
					testingEquipmentItr.setTestingEquipmentStatus("N");
				}
				listNonRemovedTestingEquipment.add(testingEquipmentItr);
			}
		}
		return listNonRemovedTestingEquipment;
	}

	public List<TestingRecords> findNonRemoveTestingRecord(List<TestingRecords> listOfTestingRecords) {
		List<TestingRecords> listNonRemovedTestingRecord = new ArrayList<TestingRecords>();
		for (TestingRecords testingRecords : listOfTestingRecords) {
			if (testingRecords.getTestingRecordStatus() ==null || !testingRecords.getTestingRecordStatus().equalsIgnoreCase("R")) {
				if(testingRecords.getTestingRecordStatus() ==null) {
					testingRecords.setTestingRecordStatus("N");
				}
				listNonRemovedTestingRecord.add(testingRecords);
			}
		}
		return listNonRemovedTestingRecord;
	}

	public Set<SignatorDetails> findNonRemovedReport(Set<SignatorDetails> signatorDetails) {
		Set<SignatorDetails> signatorDetail = new HashSet<SignatorDetails>();
		for (SignatorDetails signatorDetailItr : signatorDetails) {
			if (signatorDetailItr.getSignatorStatus() == null
					|| !signatorDetailItr.getSignatorStatus().equalsIgnoreCase("R")) {
				if(signatorDetailItr.getSignatorStatus() == null) {
					signatorDetailItr.setSignatorStatus("N");
				}
				signatorDetail.add(signatorDetailItr);
			}
		}
		return signatorDetail;
	}

	public List<SummaryObervation> findNonRemoveObservation(List<SummaryObervation> summaryObervation) {
		List<SummaryObervation> obervationList = new ArrayList<SummaryObervation>();
		for (SummaryObervation obervation : summaryObervation) {
			if (obervation.getObervationStatus() == null
					|| !obervation.getObervationStatus().equalsIgnoreCase("R")) {
				if(obervation.getObervationStatus() == null) {
					obervation.setObervationStatus("N");
				}
				obervationList.add(obervation);
			}
		}
		return obervationList;
	}

	public List<CircuitBreaker> findNonRemovedCircuitBreaker(List<CircuitBreaker> circuitBreaker) {
		List<CircuitBreaker> circuitBreakerList = new ArrayList<CircuitBreaker>();
		for (CircuitBreaker circuitBreakerItr : circuitBreaker) {
			if (circuitBreakerItr.getCircuitStatus() == null
					|| !circuitBreakerItr.getCircuitStatus().equalsIgnoreCase("R")) {
				if(circuitBreakerItr.getCircuitStatus() == null) {
					circuitBreakerItr.setCircuitStatus("N");
				}
				circuitBreakerList.add(circuitBreakerItr);
			}
		}
		return circuitBreakerList;
	}

	public List<SupplyParameters> findNonRemovedSupplyParameters(List<SupplyParameters> supplyParameters) {
		List<SupplyParameters> supplyParametersList = new ArrayList<SupplyParameters>();
		for (SupplyParameters supplyParametersItr : supplyParameters) {
			if (supplyParametersItr.getSupplyParameterStatus() == null
					&& !supplyParametersItr.getSupplyParameterStatus().equalsIgnoreCase("R")) {
				if(supplyParametersItr.getSupplyParameterStatus() == null) {
					supplyParametersItr.setSupplyParameterStatus("N");
				}
				supplyParametersList.add(supplyParametersItr);
			}
		}
		return supplyParametersList;
	}

}
