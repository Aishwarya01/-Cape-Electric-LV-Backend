package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.capeelectric.exception.DecimalConversionException;
import com.capeelectric.util.Constants;
import com.capeelectric.util.DecimalConversion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@Column(name = "CIRCUIT_MAKE")
	private String circuitMake;

	@Column(name = "CIRCUIT_TYPE")
	private String circuitType;

	@Column(name = "CIRCUIT_NUMBER_OF_POLES")
	private String numberOfPoles;

	@Column(name = "CIRCUIT_CURRENT_CURVE")
	private String circuitCurrentCurve;

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

	@Column(name = "TESTING_RECORD_STATUS")
	private String testingRecordStatus;
	
	@Column(name = "RCD_TYPE")
	private String rcdType;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TEST_DIST_RECORD_ID")
	private TestDistRecords testDistRecords;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "testingRecords", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TestingRecordsSourceSupply> testingRecordsSourceSupply;
	
	public Integer getTestingRecordId() {
		return testingRecordId;
	}

	public void setTestingRecordId(Integer testingRecordId) {
		this.testingRecordId = testingRecordId;
	}

	public String getNumberOfPoles() {
		return numberOfPoles;
	}

	public void setNumberOfPoles(String numberOfPoles) {
		this.numberOfPoles = numberOfPoles;
	}

	public String getCircuitCurrentCurve() {
		return circuitCurrentCurve;
	}

	public void setCircuitCurrentCurve(String circuitCurrentCurve) {
		this.circuitCurrentCurve = circuitCurrentCurve;
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

	public String getCircuitMake() {
		return circuitMake;
	}

	public void setCircuitMake(String circuitMake) {
		this.circuitMake = circuitMake;
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
			this.shortCircuitSetting = DecimalConversion.convertToDecimal(shortCircuitSetting,
					Constants.test_Short_CircuitSetting);
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
		if (conductorPhase != null) {
			this.conductorPhase = DecimalConversion.oneDigitConvertion(conductorPhase);
		} else {
			this.conductorPhase = conductorPhase;
		}
	}

	public String getConductorNeutral() {
		return conductorNeutral;
	}

	public void setConductorNeutral(String conductorNeutral) {
		if (conductorNeutral != null) {
			this.conductorNeutral = DecimalConversion.oneDigitConvertion(conductorNeutral);
		} else {
			this.conductorNeutral = conductorNeutral;
		}
	}

	public String getConductorPecpc() {
		return conductorPecpc;
	}

	public void setConductorPecpc(String conductorPecpc) {
		if (conductorPecpc != null) {
			this.conductorPecpc = DecimalConversion.oneDigitConvertion(conductorPecpc);
		} else {
			this.conductorPecpc = conductorPecpc;
		}
	}

	public String getContinutiyApproximateLength() {
		return continutiyApproximateLength;
	}

	public void setContinutiyApproximateLength(String continutiyApproximateLength) {
		if (continutiyApproximateLength != null) {
			this.continutiyApproximateLength = DecimalConversion.oneDigitConvertion(continutiyApproximateLength);
		} else {
			this.continutiyApproximateLength = continutiyApproximateLength;
		}
	}

	public String getContinutiyRR() {
		return continutiyRR;
	}

	public void setContinutiyRR(String continutiyRR) {
		if (continutiyRR != null) {
			this.continutiyRR = DecimalConversion.threeDigitConvertion(continutiyRR);
		} else {
			this.continutiyRR = continutiyRR;
		}
	}

	public String getContinutiyR() {
		return continutiyR;
	}

	public void setContinutiyR(String continutiyR) {
		if (continutiyR != null) {
			this.continutiyR = DecimalConversion.threeDigitConvertion(continutiyR);
		} else {
			this.continutiyR = continutiyR;
		}
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
		this.insulationResistance = DecimalConversion.convertToDecimal(insulationResistance,
				Constants.test_Insulation_Resistance);
	}

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

	public String getDisconnectionTime() {
		return disconnectionTime;
	}

	public void setDisconnectionTime(String disconnectionTime) throws DecimalConversionException {
		this.disconnectionTime = DecimalConversion.convertToDecimal(disconnectionTime, Constants.test_DisConnection);
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

	public TestDistRecords getTestDistRecords() {
		return testDistRecords;
	}

	public void setTestDistRecords(TestDistRecords testDistRecords) {
		this.testDistRecords = testDistRecords;
	}

	public String getTestingRecordStatus() {
		return testingRecordStatus;
	}

	public void setTestingRecordStatus(String testingRecordStatus) {
		this.testingRecordStatus = testingRecordStatus;
	}

	public List<TestingRecordsSourceSupply> getTestingRecordsSourceSupply() {
		return testingRecordsSourceSupply;
	}

	public void setTestingRecordsSourceSupply(List<TestingRecordsSourceSupply> testingRecordsSourceSupply) {
		this.testingRecordsSourceSupply = testingRecordsSourceSupply;
	}

	public String getRcdType() {
		return rcdType;
	}

	public void setRcdType(String rcdType) {
		this.rcdType = rcdType;
	}

}
