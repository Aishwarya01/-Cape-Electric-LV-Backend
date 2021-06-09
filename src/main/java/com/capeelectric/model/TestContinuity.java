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
@Table(name = "TESTING_CONTINUITY_TABLE")
public class TestContinuity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_CONTINUITY_ID")
	private Integer testingContinuityId;

	@Column(name = "APPROXIMATE_LENGTH")
	private Integer approximateLength;

	@Column(name = "R1_R2")
	private Integer rRContinuity;

	@Column(name = "R2")
	private Integer rContinuity;

	@Column(name = "L_L")
	private Integer lLContinuity;

	@Column(name = "L_E")
	private Integer lEContinuity;

	@Column(name = "POLARITY")
	private String polarity;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getTestingContinuityId() {
		return testingContinuityId;
	}

	public void setTestingContinuityId(Integer testingContinuityId) {
		this.testingContinuityId = testingContinuityId;
	}

	public Integer getApproximateLength() {
		return approximateLength;
	}

	public void setApproximateLength(Integer approximateLength) {
		this.approximateLength = approximateLength;
	}

	public Integer getrRContinuity() {
		return rRContinuity;
	}

	public void setrRContinuity(Integer rRContinuity) {
		this.rRContinuity = rRContinuity;
	}

	public Integer getrContinuity() {
		return rContinuity;
	}

	public void setrContinuity(Integer rContinuity) {
		this.rContinuity = rContinuity;
	}

	public Integer getlLContinuity() {
		return lLContinuity;
	}

	public void setlLContinuity(Integer lLContinuity) {
		this.lLContinuity = lLContinuity;
	}

	public Integer getlEContinuity() {
		return lEContinuity;
	}

	public void setlEContinuity(Integer lEContinuity) {
		this.lEContinuity = lEContinuity;
	}

	public String getPolarity() {
		return polarity;
	}

	public void setPolarity(String polarity) {
		this.polarity = polarity;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}
	
}
