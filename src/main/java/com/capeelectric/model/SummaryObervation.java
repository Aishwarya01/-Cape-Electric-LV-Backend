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
@Table(name = "observations_table")
public class SummaryObervation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OBSERVATIONS_ID")
	private Integer observationsId;

	@Column(name = "OBSERVATIONS_SUPPLY")
	private String observationsSupply;
	
	@Column(name = "OBSERVATIONS_INSPECTION")
	private String observationsInspection;
	
	@Column(name = "OBSERVATIONS_TESTING")
	private String observationsTesting;

	@Column(name = "REFERANCE_NUMBER_REPORT")
	private String referanceNumberReport;

	@Column(name = "FURTHER_ACTIONS")
	private String furtherActions;

	@Column(name = "COMMENT")
	private String comment;
	
	@Column(name = "OBERVATION_STATUS")
	private String obervationStatus;

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

    public String getObservationsSupply() {
		return observationsSupply;
	}

	public void setObservationsSupply(String observationsSupply) {
		this.observationsSupply = observationsSupply;
	}

	public String getObservationsInspection() {
		return observationsInspection;
	}

	public void setObservationsInspection(String observationsInspection) {
		this.observationsInspection = observationsInspection;
	}

	public String getObservationsTesting() {
		return observationsTesting;
	}

	public void setObservationsTesting(String observationsTesting) {
		this.observationsTesting = observationsTesting;
	}

	public String getReferanceNumberReport() {
		return referanceNumberReport;
	}

	public void setReferanceNumberReport(String referanceNumberReport) {
		this.referanceNumberReport = referanceNumberReport;
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

	public void setComment(String Comment) {
		this.comment = Comment;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public String getObervationStatus() {
		return obervationStatus;
	}

	public void setObervationStatus(String obervationStatus) {
		this.obervationStatus = obervationStatus;
	}

}
