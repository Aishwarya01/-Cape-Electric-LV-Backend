package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "testing_inner_obervations_table")
public class TestingInnerObservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_INNER_OBSERVATIONS_ID")
	private Integer testingInnerObervationsId;

	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@Column(name = "OBSERVATION_DESCRIPTION")
	private String observationDescription;
	
	@Column(name = "TESTING_INNER_OBSERVATION_STATUS")
	private String testingInnerObservationStatus;  

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_OUTER_OBSERVATION_ID")
	private TestingOuterObservation testingOuterObservation;

	public Integer getTestingInnerObervationsId() {
		return testingInnerObervationsId;
	}

	public void setTestingInnerObervationsId(Integer testingInnerObervationsId) {
		this.testingInnerObervationsId = testingInnerObervationsId;
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

	public TestingOuterObservation getTestingOuterObservation() {
		return testingOuterObservation;
	}

	public void setTestingOuterObservation(TestingOuterObservation testingOuterObservation) {
		this.testingOuterObservation = testingOuterObservation;
	}

	public String getTestingInnerObservationStatus() {
		return testingInnerObservationStatus;
	}

	public void setTestingInnerObservationStatus(String testingInnerObservationStatus) {
		this.testingInnerObservationStatus = testingInnerObservationStatus;
	}

}