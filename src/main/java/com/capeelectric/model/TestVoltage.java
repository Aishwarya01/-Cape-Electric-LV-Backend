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

import com.capeelectric.exception.PeriodicTestingException;
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
	@OneToOne
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

	public void setRyVoltage(String ryVoltage) {
		this.ryVoltage = String.format("%.2f", Double.parseDouble(ryVoltage));
	}

	public String getRbVoltage() {
		return rbVoltage;
	}

	public void setRbVoltage(String rbVoltage) throws PeriodicTestingException {
		this.rbVoltage = decimalFormate(rbVoltage);
	}

	public String getYbVoltage() {
		return ybVoltage;
	}

	public void setYbVoltage(String ybVoltage) throws PeriodicTestingException {
		this.ybVoltage = decimalFormate(ybVoltage);
	}

	public String getRnVoltage() {
		return rnVoltage;
	}

	public void setRnVoltage(String rnVoltage) throws PeriodicTestingException {
		this.rnVoltage = decimalFormate(rnVoltage);
	}

	public String getYnVoltage() {
		return ynVoltage;
	}

	public void setYnVoltage(String ynVoltage) throws PeriodicTestingException {
		this.ynVoltage = decimalFormate(ynVoltage);
	}

	public String getBnVoltage() {
		return bnVoltage;
	}

	public void setBnVoltage(String bnVoltage) throws PeriodicTestingException {
		this.bnVoltage = decimalFormate(bnVoltage);
	}

	public String getRpeVoltage() {
		return rpeVoltage;
	}

	public void setRpeVoltage(String rpeVoltage) throws PeriodicTestingException {
		this.rpeVoltage = decimalFormate(rpeVoltage);
	}

	public String getYpeVoltage() {
		return ypeVoltage;
	}

	public void setYpeVoltage(String ypeVoltage) throws PeriodicTestingException {
		this.ypeVoltage = decimalFormate(ypeVoltage);
	}

	public String getBpeVoltage() {
		return bpeVoltage;
	}

	public void setBpeVoltage(String bpeVoltage) throws PeriodicTestingException {
		this.bpeVoltage = decimalFormate(bpeVoltage);
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

	private String decimalFormate(String values) throws PeriodicTestingException {
		if (values != null && !values.isEmpty()) {
			return String.format("%.2f", Double.parseDouble(values));
		} else {
			throw new PeriodicTestingException("invalid input of Voltage value");
		}

	}

}
