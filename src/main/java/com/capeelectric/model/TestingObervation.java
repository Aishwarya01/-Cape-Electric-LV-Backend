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
@Table(name = "testing_obervation_table")
public class TestingObervation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_OBSERVATION_ID")
	private Integer testingObservationId;

	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_REPORT_ID")
	private TestingReport testingReport;

	@JsonManagedReference
	@OneToMany(mappedBy = "testingObervation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestingObervations> testingObervations;

	public Integer getTestingObservationId() {
		return testingObservationId;
	}

	public void setTestingObservationId(Integer testingObservationId) {
		this.testingObservationId = testingObservationId;
	}

	public String getObservationComponentDetails() {
		return observationComponentDetails;
	}

	public void setObservationComponentDetails(String observationComponentDetails) {
		this.observationComponentDetails = observationComponentDetails;
	}

	public TestingReport getTestingReport() {
		return testingReport;
	}

	public void setTestingReport(TestingReport testingReport) {
		this.testingReport = testingReport;
	}

	public List<TestingObervations> getTestingObervations() {
		return testingObervations;
	}

	public void setTestingObervations(List<TestingObervations> testingObervations) {
		this.testingObervations = testingObervations;
	}
}
