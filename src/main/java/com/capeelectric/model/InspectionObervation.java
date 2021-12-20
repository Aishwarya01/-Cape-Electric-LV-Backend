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
@Table(name = "inspection_obervation_table")
public class InspectionObervation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSPECTION_OBSERVATION_ID")
	private Integer inspectionObservationId;

	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "PERIODIC_INSPECTION_ID")
	private PeriodicInspection periodicInspection;

	@JsonManagedReference
	@OneToMany(mappedBy = "inspectionObervation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<InspectionObervations> inspectionObervations;

	public Integer getInspectionObservationId() {
		return inspectionObservationId;
	}

	public void setInspectionObservationId(Integer inspectionObservationId) {
		this.inspectionObservationId = inspectionObservationId;
	}

	public String getObservationComponentDetails() {
		return observationComponentDetails;
	}

	public void setObservationComponentDetails(String observationComponentDetails) {
		this.observationComponentDetails = observationComponentDetails;
	}

	public PeriodicInspection getPeriodicInspection() {
		return periodicInspection;
	}

	public void setPeriodicInspection(PeriodicInspection periodicInspection) {
		this.periodicInspection = periodicInspection;
	}

	public List<InspectionObervations> getInspectionObervations() {
		return inspectionObervations;
	}

	public void setInspectionObervations(List<InspectionObervations> inspectionObervations) {
		this.inspectionObervations = inspectionObervations;
	}

}
