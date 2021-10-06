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
@Table(name = "testing_records_table")
public class TestingRecords implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_RECORD_ID")
	private Integer testingRecordId;

	@Column(name = "CIRCUIT_NO")
	private String circuitNo;

	@Column(name = "CIRCUIT_DESC")
	private String circuitDesc;

	@Column(name = "CIRCUIT_STANDARD_NO")
	private String circuitStandardNo;

	@Column(name = "CIRCUIT_TYPE")
	private String circuitType;

	@Column(name = "CIRCUIT_RATING")
	private String circuitRating;

	@Column(name = "CIRCUIT_BREAKING_CAPACITY")
	private String circuitBreakingCapacity;
	
	@Column(name = "SHORT_CIRCUIT_SETTING")
	private String shortCircuitSetting;
	
	@Column(name = "E_F_SETTING")
	private String eFSetting;

	@Column(name = "CONDUCTOR_INSTALLATION")
	private String conductorInstallation;

	@Column(name = "CONDUCTOR_PHASE")
	private String conductorPhase;
	
	@Column(name = "CONDUCTOR_NEUTRAL")
	private String conductorNeutral;

	@Column(name = "CONDUCTOR_PECPC")
	private String conductorPecpc;

	@Column(name = "CONTINUTIY_APPROXIMATELENGTH")
	private String continutiyApproximateLength;

	@Column(name = "CONTINUTIY_R1_R2")
	private String continutiyRR;

	@Column(name = "CONTINUTIY_R2")
	private String continutiyR;

	@Column(name = "CONTINUTIY_POLARITY")
	private String continutiyPolarity;

	@Column(name = "INSULATION_RESISTANCE")
	private String insulationResistance;
	
	@Column(name = "TEST_VOLTAGE")
	private String testVoltage;

	@Column(name = "TEST_LOOPIMPEDANCE")
	private String testLoopImpedance;

	@Column(name = "TEST_FAULTCURRENT")
	private String testFaultCurrent;

	@Column(name = "DISCONNECTION_TIME")
	private String disconnectionTime;

	@Column(name = "RCD_CURRENT")
	private String rcdCurrent;

	@Column(name = "RCD_OPERATINGCURRENT")
	private String rcdOperatingCurrent;

	@Column(name = "RCD_OPERATINGFIVECURRENT")
	private String rcdOperatingFiveCurrent;

	@Column(name = "RCD_TESTBUTTONOPERATION")
	private String rcdTestButtonOperation;

	@Column(name = "RCD_REMARKS")
	private String rcdRemarks;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getTestingRecordId() {
		return testingRecordId;
	}

	public void setTestingRecordId(Integer testingRecordId) {
		this.testingRecordId = testingRecordId;
	}

	public String getCircuitNo() {
		return circuitNo;
	}

	public void setCircuitNo(String circuitNo) {
		this.circuitNo = circuitNo;
	}

	public String getCircuitDesc() {
		return circuitDesc;
	}

	public void setCircuitDesc(String circuitDesc) {
		this.circuitDesc = circuitDesc;
	}

	public String getCircuitStandardNo() {
		return circuitStandardNo;
	}

	public void setCircuitStandardNo(String circuitStandardNo) {
		this.circuitStandardNo = circuitStandardNo;
	}

	public String getCircuitType() {
		return circuitType;
	}

	public void setCircuitType(String circuitType) {
		this.circuitType = circuitType;
	}

	public String getCircuitRating() {
		return circuitRating;
	}

	public void setCircuitRating(String circuitRating) {
		this.circuitRating = circuitRating;
	}

	public String getCircuitBreakingCapacity() {
		return circuitBreakingCapacity;
	}

	public void setCircuitBreakingCapacity(String circuitBreakingCapacity) {
		this.circuitBreakingCapacity = circuitBreakingCapacity;
	}

	public String getShortCircuitSetting() {
		return shortCircuitSetting;
	}

	public void setShortCircuitSetting(String shortCircuitSetting) throws DecimalConversionException {
		if (shortCircuitSetting != null) {
			this.shortCircuitSetting = DecimalConversion.convertToDecimal(shortCircuitSetting, Constants.test_Short_CircuitSetting);
		}
		this.shortCircuitSetting = shortCircuitSetting;
	}

	public String geteFSetting() {
		return eFSetting;
	}

	public void seteFSetting(String eFSetting) throws DecimalConversionException {
		if (eFSetting != null) {
			this.eFSetting = DecimalConversion.convertToDecimal(eFSetting, Constants.test_EFSetting);
		}
		this.eFSetting = eFSetting;
	}

	public String getConductorInstallation() {
		return conductorInstallation;
	}

	public void setConductorInstallation(String conductorInstallation) {
		this.conductorInstallation = conductorInstallation;
	}

	public String getConductorPhase() {
		return conductorPhase;
	}

	public void setConductorPhase(String conductorPhase) {
		this.conductorPhase = conductorPhase;
	}

	public String getConductorNeutral() {
		return conductorNeutral;
	}

	public void setConductorNeutral(String conductorNeutral) {
		this.conductorNeutral = conductorNeutral;
	}

	public String getConductorPecpc() {
		return conductorPecpc;
	}

	public void setConductorPecpc(String conductorPecpc) {
		this.conductorPecpc = conductorPecpc;
	}

	public String getContinutiyApproximateLength() {
		return continutiyApproximateLength;
	}

	public void setContinutiyApproximateLength(String continutiyApproximateLength) {
		this.continutiyApproximateLength = continutiyApproximateLength;
	}

	public String getContinutiyRR() {
		return continutiyRR;
	}

	public void setContinutiyRR(String continutiyRR) {
		this.continutiyRR = continutiyRR;
	}

	public String getContinutiyR() {
		return continutiyR;
	}

	public void setContinutiyR(String continutiyR) {
		this.continutiyR = continutiyR;
	}

	public String getContinutiyPolarity() {
		return continutiyPolarity;
	}

	public void setContinutiyPolarity(String continutiyPolarity) {
		this.continutiyPolarity = continutiyPolarity;
	}

	public String getInsulationResistance() {
		return insulationResistance;
	}

	public void setInsulationResistance(String insulationResistance) throws DecimalConversionException {
		this.insulationResistance = DecimalConversion.convertToDecimal(insulationResistance, Constants.test_Insulation_Resistance);
	}

	public String getTestVoltage() {
		return testVoltage;
	}

	public void setTestVoltage(String testVoltage) throws DecimalConversionException {
		this.testVoltage = DecimalConversion.convertToDecimal(testVoltage,Constants.test_Voltage);
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

	public String getDisconnectionTime() {
		return disconnectionTime;
	}

	public void setDisconnectionTime(String disconnectionTime) {
		this.disconnectionTime = disconnectionTime;
	}

	public String getRcdCurrent() {
		return rcdCurrent;
	}

	public void setRcdCurrent(String rcdCurrent) {
		this.rcdCurrent = rcdCurrent;
	}

	public String getRcdOperatingCurrent() {
		return rcdOperatingCurrent;
	}

	public void setRcdOperatingCurrent(String rcdOperatingCurrent) {
		this.rcdOperatingCurrent = rcdOperatingCurrent;
	}

	public String getRcdOperatingFiveCurrent() {
		return rcdOperatingFiveCurrent;
	}

	public void setRcdOperatingFiveCurrent(String rcdOperatingFiveCurrent) {
		this.rcdOperatingFiveCurrent = rcdOperatingFiveCurrent;
	}

	public String getRcdTestButtonOperation() {
		return rcdTestButtonOperation;
	}

	public void setRcdTestButtonOperation(String rcdTestButtonOperation) {
		this.rcdTestButtonOperation = rcdTestButtonOperation;
	}

	public String getRcdRemarks() {
		return rcdRemarks;
	}

	public void setRcdRemarks(String rcdRemarks) {
		this.rcdRemarks = rcdRemarks;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

}
