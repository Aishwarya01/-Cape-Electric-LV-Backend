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
@Table(name = "TESTING_CONDUCTOR_TABLE")
public class TestConductor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_CONDUCTOR_ID")
	private Integer testingConductorId;

	@Column(name = "INSTALLATION_REFERANCE_METHOD")
	private String installationReferanceMethod;

	@Column(name = "LIVE")
	private Integer live;

	@Column(name = "PE_CPC")
	private Integer pecpc;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getTestingConductorId() {
		return testingConductorId;
	}

	public void setTestingConductorId(Integer testingConductorId) {
		this.testingConductorId = testingConductorId;
	}

	public String getInstallationReferanceMethod() {
		return installationReferanceMethod;
	}

	public void setInstallationReferanceMethod(String installationReferanceMethod) {
		this.installationReferanceMethod = installationReferanceMethod;
	}

	public Integer getLive() {
		return live;
	}

	public void setLive(Integer live) {
		this.live = live;
	}

	public Integer getPecpc() {
		return pecpc;
	}

	public void setPecpc(Integer pecpc) {
		this.pecpc = pecpc;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

}
