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

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.util.DecimalConversion;
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

	@Column(name = "R_Y")
	private String ryFaultCurrent;
	
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
	@ManyToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getFaultCurrentId() {
		return faultCurrentId;
	}

	public void setFaultCurrentId(Integer faultCurrentId) {
		this.faultCurrentId = faultCurrentId;
	}

	public String getRyFaultCurrent() {
		return ryFaultCurrent;
	}

	public void setRyFaultCurrent(String ryFaultCurrent) {
		this.ryFaultCurrent = ryFaultCurrent;
	}

	public String getRbFaultCurrent() {
		return rbFaultCurrent;
	}

	public void setRbFaultCurrent(String rbFaultCurrent) throws PeriodicTestingException {
		this.rbFaultCurrent = DecimalConversion.convertToDecimal(rbFaultCurrent, "%.2f");
	}

	public String getYbFaultCurrent() {
		return ybFaultCurrent;
	}

	public void setYbFaultCurrent(String ybFaultCurrent) throws PeriodicTestingException {
		this.ybFaultCurrent = DecimalConversion.convertToDecimal(ybFaultCurrent, "%.2f");
	}

	public String getRnFaultCurrent() {
		return rnFaultCurrent;
	}

	public void setRnFaultCurrent(String rnFaultCurrent) throws PeriodicTestingException {
		this.rnFaultCurrent = DecimalConversion.convertToDecimal(rnFaultCurrent, "%.2f");
	}

	public String getYnFaultCurrent() {
		return ynFaultCurrent;
	}

	public void setYnFaultCurrent(String ynFaultCurrent) throws PeriodicTestingException {
		this.ynFaultCurrent = DecimalConversion.convertToDecimal(ynFaultCurrent, "%.2f");
	}

	public String getBnFaultCurrent() {
		return bnFaultCurrent;
	}

	public void setBnFaultCurrent(String bnFaultCurrent) throws PeriodicTestingException {
		this.bnFaultCurrent = DecimalConversion.convertToDecimal(bnFaultCurrent, "%.2f");
	}

	public String getRpeFaultCurrent() {
		return rpeFaultCurrent;
	}

	public void setRpeFaultCurrent(String rpeFaultCurrent) throws PeriodicTestingException {
		this.rpeFaultCurrent = DecimalConversion.convertToDecimal(rpeFaultCurrent, "%.2f");
	}

	public String getYpeFaultCurrent() {
		return ypeFaultCurrent;
	}

	public void setYpeFaultCurrent(String ypeFaultCurrent) throws PeriodicTestingException {
		this.ypeFaultCurrent = DecimalConversion.convertToDecimal(ypeFaultCurrent, "%.2f");
	}

	public String getBpeFaultCurrent() {
		return bpeFaultCurrent;
	}

	public void setBpeFaultCurrent(String bpeFaultCurrent) throws PeriodicTestingException {
		this.bpeFaultCurrent = DecimalConversion.convertToDecimal(bpeFaultCurrent, "%.2f");
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

}
