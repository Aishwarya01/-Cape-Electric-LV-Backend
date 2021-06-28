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
@Table(name = "TESTING_VOLTAGE_TABLE")
public class TestVoltage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_VOLTAGE_ID")
	private Integer testingVoltageId;
	
	@Column(name = "R_Y")
	private String ryVoltage;
	
	@Column(name = "R_B")
	private String rbVoltage;
	
	@Column(name = "Y_B")
	private String ybVoltage;
	
	@Column(name = "R_N")
 	private String rnVoltage;
	
	@Column(name = "Y_N")
	private String ynVoltage;
	
	@Column(name = "B_N")
	private String bnVoltage;
	
	@Column(name = "R_PE")
	private String rpeVoltage;
	
	@Column(name = "Y_PE")
	private String ypeVoltage;
	
	@Column(name = "B_PE")
	private String bpeVoltage;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getTestingVoltageId() {
		return testingVoltageId;
	}

	public void setTestingVoltageId(Integer testingVoltageId) {
		this.testingVoltageId = testingVoltageId;
	}

	public String getRyVoltage() {
		return ryVoltage;
	}

	public void setRyVoltage(String ryVoltage) throws PeriodicTestingException {
		this.ryVoltage = DecimalConversion.convertToDecimal(ryVoltage, "%.2f");
	}

	public String getRbVoltage() {
		return rbVoltage;
	}

	public void setRbVoltage(String rbVoltage) throws PeriodicTestingException {
		this.rbVoltage = DecimalConversion.convertToDecimal(rbVoltage, "%.2f");
	}

	public String getYbVoltage() {
		return ybVoltage;
	}

	public void setYbVoltage(String ybVoltage) throws PeriodicTestingException {
		this.ybVoltage = DecimalConversion.convertToDecimal(ybVoltage, "%.2f");
	}

	public String getRnVoltage() {
		return rnVoltage;
	}

	public void setRnVoltage(String rnVoltage) throws PeriodicTestingException {
		this.rnVoltage = DecimalConversion.convertToDecimal(rnVoltage, "%.2f");
	}

	public String getYnVoltage() {
		return ynVoltage;
	}

	public void setYnVoltage(String ynVoltage) throws PeriodicTestingException {
		this.ynVoltage = DecimalConversion.convertToDecimal(ynVoltage, "%.2f");
	}

	public String getBnVoltage() {
		return bnVoltage;
	}

	public void setBnVoltage(String bnVoltage) throws PeriodicTestingException {
		this.bnVoltage = DecimalConversion.convertToDecimal(bnVoltage, "%.2f");
	}

	public String getRpeVoltage() {
		return rpeVoltage;
	}

	public void setRpeVoltage(String rpeVoltage) throws PeriodicTestingException {
		this.rpeVoltage = DecimalConversion.convertToDecimal(rpeVoltage, "%.2f");
	}

	public String getYpeVoltage() {
		return ypeVoltage;
	}

	public void setYpeVoltage(String ypeVoltage) throws PeriodicTestingException {
		this.ypeVoltage = DecimalConversion.convertToDecimal(ypeVoltage, "%.2f");
	}

	public String getBpeVoltage() {
		return bpeVoltage;
	}

	public void setBpeVoltage(String bpeVoltage) throws PeriodicTestingException {
		this.bpeVoltage = DecimalConversion.convertToDecimal(bpeVoltage, "%.2f");
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

}
