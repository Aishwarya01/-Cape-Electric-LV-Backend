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

import com.capeelectric.exception.DecimalConversionException;
import com.capeelectric.util.Constants;
import com.capeelectric.util.DecimalConversion;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "testing_records_voltage")
public class TestingRecordsVoltage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VOLTAGE_ID")
	private Integer voltageId;

	@Column(name = "TEST_VOLTAGE")
	private String testVoltage;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_RECORD_ID")
	private TestingRecords testingRecords;

	public Integer getVoltageId() {
		return voltageId;
	}

	public void setVoltageId(Integer voltageId) {
		this.voltageId = voltageId;
	}

	public String getTestVoltage() {
		return testVoltage;
	}

	public void setTestVoltage(String testVoltage) throws DecimalConversionException {
		this.testVoltage = DecimalConversion.convertToDecimal(testVoltage, Constants.test_Voltage);
	}

	public TestingRecords getTestingRecords() {
		return testingRecords;
	}

	public void setTestingRecords(TestingRecords testingRecords) {
		this.testingRecords = testingRecords;
	}

}
