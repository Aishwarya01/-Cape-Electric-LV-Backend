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
@Table(name = "supply_obervations_table")
public class SupplyObervations implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUPPLY_OBSERVATIONS_ID")
	private Integer supplyObervationsId;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUPPLY_OBSERVATION_ID")
	private SupplyObservation supplyObservation;

	public Integer getSupplyObervationsId() {
		return supplyObervationsId;
	}

	public void setSupplyObervationsId(Integer supplyObervationsId) {
		this.supplyObervationsId = supplyObervationsId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public SupplyObservation getSupplyObservation() {
		return supplyObservation;
	}

	public void setSupplyObservation(SupplyObservation supplyObservation) {
		this.supplyObservation = supplyObservation;
	}
 

}
