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
@Table(name = "inspection_inner_obervations_table")
public class InspectionInnerObervations implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSPECTION_INNER_OBSERVATIONS_ID")
	private Integer inspectionInnerObervationsId;

	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@Column(name = "OBSERVATION_DESCRIPTION")
	private String observationDescription;
	
	@Column(name = "INSPECTION_INNER_OBERVATION_STATUS")
	private String inspectionInnerObervationStatus;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "INSPECTION_OUTER_OBSERVATION_ID")
	private InspectionOuterObservation inspectionOuterObservation;

	public Integer getInspectionInnerObervationsId() {
		return inspectionInnerObervationsId;
	}

	public void setInspectionInnerObervationsId(Integer inspectionInnerObervationsId) {
		this.inspectionInnerObervationsId = inspectionInnerObervationsId;
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

	public InspectionOuterObservation getInspectionOuterObservation() {
		return inspectionOuterObservation;
	}

	public void setInspectionOuterObservation(InspectionOuterObservation inspectionOuterObservation) {
		this.inspectionOuterObservation = inspectionOuterObservation;
	}

	public String getInspectionInnerObervationStatus() {
		return inspectionInnerObervationStatus;
	}

	public void setInspectionInnerObervationStatus(String inspectionInnerObervationStatus) {
		this.inspectionInnerObervationStatus = inspectionInnerObervationStatus;
	}

}
