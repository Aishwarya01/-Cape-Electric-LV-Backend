package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pi_observations_add database table.
 * 
 */
@Entity
@Table(name="pi_observations_add")
@NamedQuery(name="PiObservationsAdd.findAll", query="SELECT p FROM PiObservationsAdd p")
public class PiObservationsAdd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OBSERVATION_ADD_KEY")
	private int observationAddKey;

	@Column(name="CLASSIFICATION_CODE")
	private String classificationCode;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="FURTHER_INVESTIGATION")
	private String furtherInvestigation;

	private String observations;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional one-to-one association to PiObservation
	@OneToOne
	@JoinColumn(name="OBSERVATION_ADD_KEY")
	private PiObservation piObservation;

	public PiObservationsAdd() {
	}

	public int getObservationAddKey() {
		return this.observationAddKey;
	}

	public void setObservationAddKey(int observationAddKey) {
		this.observationAddKey = observationAddKey;
	}

	public String getClassificationCode() {
		return this.classificationCode;
	}

	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getFurtherInvestigation() {
		return this.furtherInvestigation;
	}

	public void setFurtherInvestigation(String furtherInvestigation) {
		this.furtherInvestigation = furtherInvestigation;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdateddt() {
		return this.updateddt;
	}

	public void setUpdateddt(Date updateddt) {
		this.updateddt = updateddt;
	}

	public PiObservation getPiObservation() {
		return this.piObservation;
	}

	public void setPiObservation(PiObservation piObservation) {
		this.piObservation = piObservation;
	}

}