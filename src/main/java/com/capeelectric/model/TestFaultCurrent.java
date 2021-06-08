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
@Table(name = "TESTING_FAULTCURRENT_TABLE")
public class TestFaultCurrent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_FAULTCURRENT_ID")
	private Integer faultCurrentId;
	
	@Column(name = "R_B")
	private String rbFaultCurrent;
	
	@Column(name = "Y_B")
	private String ybFaultCurrent;
	
	@Column(name = "R_N")
 	private String rnFaultCurrent;
	
	@Column(name = "Y_N")
	private String ynFaultCurrent;
	
	@Column(name = "B_N")
	private String bnFaultCurrent;
	
	@Column(name = "R_PE")
	private String rpeFaultCurrent;
	
	@Column(name = "Y_PE")
	private String ypeFaultCurrent;
	
	@Column(name = "B_PE")
	private String bpeFaultCurrent;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getFaultCurrentId() {
		return faultCurrentId;
	}

	public void setFaultCurrentId(Integer faultCurrentId) {
		this.faultCurrentId = faultCurrentId;
	}

	public String getRbFaultCurrent() {
		return rbFaultCurrent;
	}

	public void setRbFaultCurrent(String rbFaultCurrent) {
		this.rbFaultCurrent = rbFaultCurrent;
	}

	public String getYbFaultCurrent() {
		return ybFaultCurrent;
	}

	public void setYbFaultCurrent(String ybFaultCurrent) {
		this.ybFaultCurrent = ybFaultCurrent;
	}

	public String getRnFaultCurrent() {
		return rnFaultCurrent;
	}

	public void setRnFaultCurrent(String rnFaultCurrent) {
		this.rnFaultCurrent = rnFaultCurrent;
	}

	public String getYnFaultCurrent() {
		return ynFaultCurrent;
	}

	public void setYnFaultCurrent(String ynFaultCurrent) {
		this.ynFaultCurrent = ynFaultCurrent;
	}

	public String getBnFaultCurrent() {
		return bnFaultCurrent;
	}

	public void setBnFaultCurrent(String bnFaultCurrent) {
		this.bnFaultCurrent = bnFaultCurrent;
	}

	public String getRpeFaultCurrent() {
		return rpeFaultCurrent;
	}

	public void setRpeFaultCurrent(String rpeFaultCurrent) {
		this.rpeFaultCurrent = rpeFaultCurrent;
	}

	public String getYpeFaultCurrent() {
		return ypeFaultCurrent;
	}

	public void setYpeFaultCurrent(String ypeFaultCurrent) {
		this.ypeFaultCurrent = ypeFaultCurrent;
	}

	public String getBpeFaultCurrent() {
		return bpeFaultCurrent;
	}

	public void setBpeFaultCurrent(String bpeFaultCurrent) {
		this.bpeFaultCurrent = bpeFaultCurrent;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}
	
}
