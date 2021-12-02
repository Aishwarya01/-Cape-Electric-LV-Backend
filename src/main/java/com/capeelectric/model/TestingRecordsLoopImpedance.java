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
@Table(name = "testing_records_loopimpedance")
public class TestingRecordsLoopImpedance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOOPIMPEDANCE_ID")
	private Integer loopImpedanceId;

	@Column(name = "TEST_LOOPIMPEDANCE")
	private String testLoopImpedance;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_RECORD_ID")
	private TestingRecords testingRecords;

	public Integer getLoopImpedanceId() {
		return loopImpedanceId;
	}

	public void setLoopImpedanceId(Integer loopImpedanceId) {
		this.loopImpedanceId = loopImpedanceId;
	}

	public String getTestLoopImpedance() {
		return testLoopImpedance;
	}

	public void setTestLoopImpedance(String testLoopImpedance) throws DecimalConversionException {
		this.testLoopImpedance = DecimalConversion.convertToDecimal(testLoopImpedance, Constants.test_Loopimpedance);
	}

	public TestingRecords getTestingRecords() {
		return testingRecords;
	}

	public void setTestingRecords(TestingRecords testingRecords) {
		this.testingRecords = testingRecords;
	}

}
