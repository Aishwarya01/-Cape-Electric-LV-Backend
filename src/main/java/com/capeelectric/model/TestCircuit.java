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
@Table(name = "TESTING_CIRCUIT_TABLE")
public class TestCircuit implements Serializable{
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_CIRCUIT_ID")
	private Integer circuitID;
	
	@Column(name = "CIRCUIT_NO")
	private Integer circuitNo;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "STANDARD_NO")
	private String standardNo;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "RATING")
	private Integer rating;
	
	@Column(name = "BREAKING_CAPACITY")
	private Integer breakingCapacity;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getCircuitID() {
		return circuitID;
	}

	public void setCircuitID(Integer circuitID) {
		this.circuitID = circuitID;
	}

	public Integer getCircuitNo() {
		return circuitNo;
	}

	public void setCircuitNo(Integer circuitNo) {
		this.circuitNo = circuitNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStandardNo() {
		return standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getBreakingCapacity() {
		return breakingCapacity;
	}

	public void setBreakingCapacity(Integer breakingCapacity) {
		this.breakingCapacity = breakingCapacity;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}
	
}
