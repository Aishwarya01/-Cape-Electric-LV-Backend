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

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "summary_observations_table")
public class SummaryObservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBSERVATIONS_ID")
	private Integer observationsId;

	@Column(name = "FURTHER_ACTIONS")
	private String furtherActions;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "OBERVATION_STATUS")
	private String obervationStatus;
	
	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@Column(name = "OBSERVATIONS")
	private String observations;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUMMARY_ID")
	private Summary summary;
	
	public Integer getObservationsId() {
		return observationsId;
	}

	public void setObservationsId(Integer observationsId) {
		this.observationsId = observationsId;
	}

	public String getFurtherActions() {
		return furtherActions;
	}

	public void setFurtherActions(String furtherActions) {
		this.furtherActions = furtherActions;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getObervationStatus() {
		return obervationStatus;
	}

	public void setObervationStatus(String obervationStatus) {
		this.obervationStatus = obervationStatus;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public String getObservationComponentDetails() {
		return observationComponentDetails;
	}

	public void setObservationComponentDetails(String observationComponentDetails) {
		this.observationComponentDetails = observationComponentDetails;
	}
	
	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

}