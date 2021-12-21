package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "testing_outer_obervation_table")
public class TestingOuterObservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_OUTER_OBSERVATION_ID")
	private Integer testingOuterObservationId;

	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@Column(name = "OBSERVATION_DESCRIPTION")
	private String observationDescription;
	
	@Column(name = "TESTING_OUTER_OBSERVATION_STATUS")
	private String testingOuterObservationStatus;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_REPORT_ID")
	private TestingReport testingReport;

	@JsonManagedReference
	@OneToMany(mappedBy = "testingOuterObservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestingInnerObservation> testingInnerObservation;

	public Integer getTestingOuterObservationId() {
		return testingOuterObservationId;
	}

	public void setTestingOuterObservationId(Integer testingOuterObservationId) {
		this.testingOuterObservationId = testingOuterObservationId;
	}

	public String getObservationComponentDetails() {
		return observationComponentDetails;
	}

	public void setObservationComponentDetails(String observationComponentDetails) {
		this.observationComponentDetails = observationComponentDetails;
	}

	public String getObservationDescription() {
		return observationDescription;
	}

	public void setObservationDescription(String observationDescription) {
		this.observationDescription = observationDescription;
	}

	public TestingReport getTestingReport() {
		return testingReport;
	}

	public void setTestingReport(TestingReport testingReport) {
		this.testingReport = testingReport;
	}

	public List<TestingInnerObservation> getTestingInnerObservation() {
		return testingInnerObservation;
	}

	public void setTestingInnerObservation(List<TestingInnerObservation> testingInnerObservation) {
		this.testingInnerObservation = testingInnerObservation;
	}

	public String getTestingOuterObservationStatus() {
		return testingOuterObservationStatus;
	}

	public void setTestingOuterObservationStatus(String testingOuterObservationStatus) {
		this.testingOuterObservationStatus = testingOuterObservationStatus;
	}

}