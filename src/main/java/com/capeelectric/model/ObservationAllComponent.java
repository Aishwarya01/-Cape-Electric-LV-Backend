package com.capeelectric.model;

import java.util.List;

/**
 *
 * @author capeelectricsoftware
 *
 */
public class ObservationAllComponent {

	private List<SupplyOuterObservation> supplyOuterObservation;

	private List<InspectionOuterObservation> InspectionOuterObservation;

	private List<TestingOuterObservation> testingOuterObservation;

	public List<SupplyOuterObservation> getSupplyOuterObservation() {
		return supplyOuterObservation;
	}

	public void setSupplyOuterObservation(List<SupplyOuterObservation> supplyOuterObservation) {
		this.supplyOuterObservation = supplyOuterObservation;
	}

	public List<InspectionOuterObservation> getInspectionOuterObservation() {
		return InspectionOuterObservation;
	}

	public void setInspectionOuterObservation(List<InspectionOuterObservation> inspectionOuterObservation) {
		InspectionOuterObservation = inspectionOuterObservation;
	}

	public List<TestingOuterObservation> getTestingOuterObservation() {
		return testingOuterObservation;
	}

	public void setTestingOuterObservation(List<TestingOuterObservation> testingOuterObservation) {
		this.testingOuterObservation = testingOuterObservation;
	}

}
