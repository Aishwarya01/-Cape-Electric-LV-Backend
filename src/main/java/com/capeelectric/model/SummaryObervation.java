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

	@Column(name = "OBSERVATIONS")
	private String observations;

	@Column(name = "REFERANCE_NUMBER_REPORT")
	private String referanceNumberReport;

	@Column(name = "FURTHER_ACTIONS")
	private String furtherActions;

	@Column(name = "COMMENT")
	private String comment;

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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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

}
