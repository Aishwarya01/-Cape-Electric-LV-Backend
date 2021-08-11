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
@Table(name = "circuit_breaker_table")
public class CircuitBreaker implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CIRCUIT_BREAKER_ID")
	private Integer circuitBreakerId;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "POLES_NO")
	private String noPoles;

	@Column(name = "CURRRENT")
	private String current;
	
	@Column(name = "VOLTAGE")
	private String voltage;

	@Column(name = "FUSE")
	private String fuse;

	@Column(name = "RESIDUAL_CURRENT")
	private String residualCurrent;

	@Column(name = "RESIDUAL_TIME")
	private String residualTime;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUPPLY_CHARACTERISTICS_ID")
	private SupplyCharacteristics supplyCharacteristics;

	public Integer getCircuitBreakerId() {
		return circuitBreakerId;
	}

	public void setCircuitBreakerId(Integer circuitBreakerId) {
		this.circuitBreakerId = circuitBreakerId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNoPoles() {
		return noPoles;
	}

	public void setNoPoles(String noPoles) {
		this.noPoles = noPoles;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getFuse() {
		return fuse;
	}

	public void setFuse(String fuse) {
		this.fuse = fuse;
	}

	public String getResidualCurrent() {
		return residualCurrent;
	}

	public void setResidualCurrent(String residualCurrent) {
		this.residualCurrent = residualCurrent;
	}

	public String getResidualTime() {
		return residualTime;
	}

	public void setResidualTime(String residualTime) {
		this.residualTime = residualTime;
	}

	public SupplyCharacteristics getSupplyCharacteristics() {
		return supplyCharacteristics;
	}

	public void setSupplyCharacteristics(SupplyCharacteristics supplyCharacteristics) {
		this.supplyCharacteristics = supplyCharacteristics;
	}

}
