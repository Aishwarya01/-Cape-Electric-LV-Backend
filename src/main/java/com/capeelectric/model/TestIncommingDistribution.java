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
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "testing_incomming_distribution_table")
public class TestIncommingDistribution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INCOMMING_DISTRIBUTION_ID")
	private Integer incommingDistributionId;

	@Column(name = "INCOMING_VOLTAGE")
	private String incomingVoltage;

	@Column(name = "INCOMING_LOOPIMPEDANCE")
	private String incomingLoopImpedance;

	@Column(name = "INCOMING_FAULTCURRENT")
	private String incomingFaultCurrent;

	@Column(name = "SOURCE_FROM_SUPPLY")
	private String sourceFromSupply;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "DISTRIBUTION_ID")
	private TestDistribution testDistribution;

	public Integer getIncommingDistributionId() {
		return incommingDistributionId;
	}

	public void setIncommingDistributionId(Integer incommingDistributionId) {
		this.incommingDistributionId = incommingDistributionId;
	}

	public String getIncomingVoltage() {
		return incomingVoltage;
	}

	public void setIncomingVoltage(String incomingVoltage) {
		this.incomingVoltage = incomingVoltage;
	}

	public String getIncomingFaultCurrent() {
		return incomingFaultCurrent;
	}

	public void setIncomingFaultCurrent(String incomingFaultCurrent) {
		this.incomingFaultCurrent = incomingFaultCurrent;
	}

	public String getSourceFromSupply() {
		return sourceFromSupply;
	}

	public void setSourceFromSupply(String sourceFromSupply) {
		this.sourceFromSupply = sourceFromSupply;
	}

	public TestDistribution getTestDistribution() {
		return testDistribution;
	}

	public void setTestDistribution(TestDistribution testDistribution) {
		this.testDistribution = testDistribution;
	}

	public String getIncomingLoopImpedance() {
		return incomingLoopImpedance;
	}

	public void setIncomingLoopImpedance(String incomingLoopImpedance) throws DecimalConversionException {
		this.incomingLoopImpedance = incomingLoopImpedance;
	}

}
