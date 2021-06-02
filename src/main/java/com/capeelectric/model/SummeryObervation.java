package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "OBSERVATIONS_TABLE")
public class SummeryObervation implements Serializable {

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

	@Column(name = "COMENT")
	private String coment;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "SUMMERY_ID")
	private Summary summery;

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

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public Summary getSummery() {
		return summery;
	}

	public void setSummery(Summary summery) {
		this.summery = summery;
	}

}
