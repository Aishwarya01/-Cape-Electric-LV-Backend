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
@Table(name = "testing_records_sourcesupply")
public class TestingRecordsSourceSupply implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOURCESUPPLY_ID")
	private Integer sourceSupplyId;

	@Column(name = "TEST_VOLTAGE")
	private String testVoltage;

	@Column(name = "TEST_LOOPIMPEDANCE")
	private String testLoopImpedance;

	@Column(name = "TEST_FAULTCURRENT")
	private String testFaultCurrent;

	@Column(name = "DISCONNECTION_TIME")
	private String disconnectionTime;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_RECORD_ID")
	private TestingRecords testingRecords;

	public String getTestVoltage() {
		return testVoltage;
	}

	public void setTestVoltage(String testVoltage) throws DecimalConversionException {
		this.testVoltage = DecimalConversion.convertToDecimal(testVoltage, Constants.test_Voltage);
	}

	public String getTestLoopImpedance() {
		return testLoopImpedance;
	}

	public void setTestLoopImpedance(String testLoopImpedance) throws DecimalConversionException {
		this.testLoopImpedance = DecimalConversion.convertToDecimal(testLoopImpedance, Constants.test_Loopimpedance);
	}

	public String getTestFaultCurrent() {
		return testFaultCurrent;
	}

	public void setTestFaultCurrent(String testFaultCurrent) throws DecimalConversionException {
		this.testFaultCurrent = DecimalConversion.convertToDecimal(testFaultCurrent, Constants.test_Faultcurrent);
	}

	public Integer getSourceSupplyId() {
		return sourceSupplyId;
	}

	public void setSourceSupplyId(Integer sourceSupplyId) {
		this.sourceSupplyId = sourceSupplyId;
	}

	public String getDisconnectionTime() {
		return disconnectionTime;
	}

	public void setDisconnectionTime(String disconnectionTime) {
		this.disconnectionTime = disconnectionTime;
	}
	
	public TestingRecords getTestingRecords() {
		return testingRecords;
	}

	public void setTestingRecords(TestingRecords testingRecords) {
		this.testingRecords = testingRecords;
	}
	
}
