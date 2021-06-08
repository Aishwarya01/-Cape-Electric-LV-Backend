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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "DISTRIBUTION_INCOMING_VALUE_TABLE")
public class DistributionIncomingValue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INCOMING_ID")
	private Integer incomingId;

	@Column(name = "INCOMING_VOLTAGE")
	private String incomingVoltage;

	@Column(name = "INCOMING_ZS")
	private String incomingZs;

	@Column(name = "INCOMING_IPF")
	private String incomingIpf;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="DISTRIBUTION_ID")
	private TestDistribution testDistribution;

	public Integer getIncomingId() {
		return incomingId;
	}

	public void setIncomingId(Integer incomingId) {
		this.incomingId = incomingId;
	}

	public String getIncomingVoltage() {
		return incomingVoltage;
	}

	public void setIncomingVoltage(String incomingVoltage) {
		this.incomingVoltage = incomingVoltage;
	}

	public String getIncomingZs() {
		return incomingZs;
	}

	public void setIncomingZs(String incomingZs) {
		this.incomingZs = incomingZs;
	}

	public String getIncomingIpf() {
		return incomingIpf;
	}

	public void setIncomingIpf(String incomingIpf) {
		this.incomingIpf = incomingIpf;
	}

	public TestDistribution getTestDistribution() {
		return testDistribution;
	}

	public void setTestDistribution(TestDistribution testDistribution) {
		this.testDistribution = testDistribution;
	}
	
}
