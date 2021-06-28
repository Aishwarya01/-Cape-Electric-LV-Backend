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
@Table(name = "TESTING_RCD_TABLE")
public class TestRcd implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_RCD_ID")
	private Integer testingRcdId;
	
	@Column(name = "RCD_CURRENT")
	private Integer  rcdCurrent;
	
	@Column(name = "OPERATING_CURRENT")
	private Integer operatingCurrent;
	
	@Column(name = "OPERATING_FIVE_CURRENT")
	private Integer operatingFiveCurrent;
	
	@Column(name = "TEST_BUTTON_OPERATION")
	private String testButtonOperation;
	
	@Column(name = "REMARKS")
	private String remarks;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getTestingRcdId() {
		return testingRcdId;
	}

	public void setTestingRcdId(Integer testingRcdId) {
		this.testingRcdId = testingRcdId;
	}

	public Integer getRcdCurrent() {
		return rcdCurrent;
	}

	public void setRcdCurrent(Integer rcdCurrent) {
		this.rcdCurrent = rcdCurrent;
	}

	public Integer getOperatingCurrent() {
		return operatingCurrent;
	}

	public void setOperatingCurrent(Integer operatingCurrent) {
		this.operatingCurrent = operatingCurrent;
	}

	public Integer getOperatingFiveCurrent() {
		return operatingFiveCurrent;
	}

	public void setOperatingFiveCurrent(Integer operatingFiveCurrent) {
		this.operatingFiveCurrent = operatingFiveCurrent;
	}

	public String getTestButtonOperation() {
		return testButtonOperation;
	}

	public void setTestButtonOperation(String testButtonOperation) {
		this.testButtonOperation = testButtonOperation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}
	
}
