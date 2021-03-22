package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pi_observations database table.
 * 
 */
@Entity
@Table(name="pi_observations")
@NamedQuery(name="PiObservation.findAll", query="SELECT p FROM PiObservation p")
public class PiObservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="OBSERVATION_KEY")
	private int observationKey;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="NO_REMEDIAL_ACTION")
	private byte noRemedialAction;

	@Column(name="OBSERVATION_MADE")
	private byte observationMade;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional one-to-one association to PiObservationsAdd
	@OneToOne(mappedBy="piObservation")
	private PiObservationsAdd piObservationsAdd;

	public PiObservation() {
	}

	public int getObservationKey() {
		return this.observationKey;
	}

	public void setObservationKey(int observationKey) {
		this.observationKey = observationKey;
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

	public byte getNoRemedialAction() {
		return this.noRemedialAction;
	}

	public void setNoRemedialAction(byte noRemedialAction) {
		this.noRemedialAction = noRemedialAction;
	}

	public byte getObservationMade() {
		return this.observationMade;
	}

	public void setObservationMade(byte observationMade) {
		this.observationMade = observationMade;
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

	public PiObservationsAdd getPiObservationsAdd() {
		return this.piObservationsAdd;
	}

	public void setPiObservationsAdd(PiObservationsAdd piObservationsAdd) {
		this.piObservationsAdd = piObservationsAdd;
	}

}