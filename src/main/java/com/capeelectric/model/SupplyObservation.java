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
@Table(name = "supply_observation_table")
public class SupplyObservation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUPPLY_OBSERVATION_ID")
	private Integer supplyObservationId;

	@Column(name = "OBSERVATION_COMPONENT_DETAILS")
	private String observationComponentDetails;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUPPLY_CHARACTERISTICS_ID")
	private SupplyCharacteristics supplyCharacteristics;

	@JsonManagedReference
	@OneToMany(mappedBy = "supplyObservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SupplyObervations> supplyObervations;

	public Integer getSupplyObservationId() {
		return supplyObservationId;
	}

	public void setSupplyObservationId(Integer supplyObservationId) {
		this.supplyObservationId = supplyObservationId;
	}

	public String getObservationComponentDetails() {
		return observationComponentDetails;
	}

	public void setObservationComponentDetails(String observationComponentDetails) {
		this.observationComponentDetails = observationComponentDetails;
	}

	public SupplyCharacteristics getSupplyCharacteristics() {
		return supplyCharacteristics;
	}

	public void setSupplyCharacteristics(SupplyCharacteristics supplyCharacteristics) {
		this.supplyCharacteristics = supplyCharacteristics;
	}

	public List<SupplyObervations> getSupplyObervations() {
		return supplyObervations;
	}

	public void setSupplyObervations(List<SupplyObervations> supplyObervations) {
		this.supplyObervations = supplyObervations;
	}

}
