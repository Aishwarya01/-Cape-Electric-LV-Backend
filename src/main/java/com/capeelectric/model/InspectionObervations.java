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
@Table(name = "inspection_obervations_table")
public class InspectionObervations implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSPECTION_OBSERVATIONS_ID")
	private Integer inspectionObervationsId;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "INSPECTION_OBSERVATION_ID")
	private InspectionObervation inspectionObervation;

	public Integer getInspectionObervationsId() {
		return inspectionObervationsId;
	}

	public void setInspectionObervationsId(Integer inspectionObervationsId) {
		this.inspectionObervationsId = inspectionObervationsId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public InspectionObervation getInspectionObervation() {
		return inspectionObervation;
	}

	public void setInspectionObervation(InspectionObervation inspectionObervation) {
		this.inspectionObervation = inspectionObervation;
	}


}
