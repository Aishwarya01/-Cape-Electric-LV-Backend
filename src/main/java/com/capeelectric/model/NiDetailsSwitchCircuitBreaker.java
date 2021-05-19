package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ni_details_switch_circuit_breaker database table.
 * 
 */
@Entity
@Table(name="ni_details_switch_circuit_breaker")
@NamedQuery(name="NiDetailsSwitchCircuitBreaker.findAll", query="SELECT n FROM NiDetailsSwitchCircuitBreaker n")
public class NiDetailsSwitchCircuitBreaker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CIRCUIT_BREAKER_KEY")
	private int circuitBreakerKey;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="CURRENT_RATING")
	private String currentRating;

	@Column(name="FUSE_RATING")
	private String fuseRating;

	private String location;

	@Column(name="NO_OF_POLES")
	private String noOfPoles;

	@Column(name="OPERATING_CURRENT")
	private String operatingCurrent;

	@Column(name="OPERATING_TIME")
	private String operatingTime;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	public NiDetailsSwitchCircuitBreaker() {
	}

	public int getCircuitBreakerKey() {
		return this.circuitBreakerKey;
	}

	public void setCircuitBreakerKey(int circuitBreakerKey) {
		this.circuitBreakerKey = circuitBreakerKey;
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

	public String getCurrentRating() {
		return this.currentRating;
	}

	public void setCurrentRating(String currentRating) {
		this.currentRating = currentRating;
	}

	public String getFuseRating() {
		return this.fuseRating;
	}

	public void setFuseRating(String fuseRating) {
		this.fuseRating = fuseRating;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNoOfPoles() {
		return this.noOfPoles;
	}

	public void setNoOfPoles(String noOfPoles) {
		this.noOfPoles = noOfPoles;
	}

	public String getOperatingCurrent() {
		return this.operatingCurrent;
	}

	public void setOperatingCurrent(String operatingCurrent) {
		this.operatingCurrent = operatingCurrent;
	}

	public String getOperatingTime() {
		return this.operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
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

}