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
@Table(name = "testing_obervations_table")
public class TestingObervations implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_OBSERVATIONS_ID")
	private Integer testingObervationsId;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_OBSERVATION_ID")
	private TestingObervation testingObervation;

	public Integer getTestingObervationsId() {
		return testingObervationsId;
	}

	public void setTestingObervationsId(Integer testingObervationsId) {
		this.testingObervationsId = testingObervationsId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public TestingObervation getTestingObervation() {
		return testingObervation;
	}

	public void setTestingObervation(TestingObervation testingObervation) {
		this.testingObervation = testingObervation;
	}

}
