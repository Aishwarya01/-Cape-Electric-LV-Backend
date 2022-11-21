package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.capeelectric.model.ViewerRegister;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "licence_table")
public class License {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENCE_ID")
	private Integer licenceId;

	// LV
	@Column(name = "LV_NO_OF_LICENCE")
	private String lvNoOfLicence;

	@Column(name = "LV_SITE_NAME")
	private String lvSiteName;

	@Column(name = "LV_STATUS")
	private String lvStatus;

	// LPS
	@Column(name = "LPS_NO_OF_LICENCE")
	private String lpsNoOfLicence;

	@Column(name = "LPS_CLIENT_NAME")
	private String lpsclientName;

	@Column(name = "LPS_PROJECT_NAME")
	private String lpsProjectName;

	@Column(name = "LPS_STATUS")
	private String lpsStatus;

//	// EMC
//	@Column(name = "EMC_NO_OF_LICENCE")
//	private String emcNoOfLicence;
//
//	@Column(name = "EMC_CLIENT_NAME")
//	private String emcClientName;
//
//	@Column(name = "EMC_STATUS")
//	private String emcStatus;
//
//	// RISK
//	@Column(name = "RISK_NO_OF_LICENCE")
//	private String riskNoOfLicence;
//
//	@Column(name = "RISK_STATUS")
//	private String riskStatus;
//
//	@Column(name = "RISK_ORGANISATION_NAME")
//	private String riskOrganisationName;
//
//	@Column(name = "RISK_PROJECT_NAME")
//	private String riskProjectName;
	
	@Transient
	private String project;
	
	@Column(name = "USERNAME")
	private String userName;

	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
		this.licenceId = licenceId;
	}

	public String getLvNoOfLicence() {
		return lvNoOfLicence;
	}

	public void setLvNoOfLicence(String lvNoOfLicence) {
		this.lvNoOfLicence = lvNoOfLicence;
	}

	public String getLvSiteName() {
		return lvSiteName;
	}

	public void setLvSiteName(String lvSiteName) {
		this.lvSiteName = lvSiteName;
	}

	public String getLvStatus() {
		return lvStatus;
	}

	public void setLvStatus(String lvStatus) {
		this.lvStatus = lvStatus;
	}

	public String getLpsNoOfLicence() {
		return lpsNoOfLicence;
	}

	public void setLpsNoOfLicence(String lpsNoOfLicence) {
		this.lpsNoOfLicence = lpsNoOfLicence;
	}

	public String getLpsclientName() {
		return lpsclientName;
	}

	public void setLpsclientName(String lpsclientName) {
		this.lpsclientName = lpsclientName;
	}

	public String getLpsProjectName() {
		return lpsProjectName;
	}

	public void setLpsProjectName(String lpsProjectName) {
		this.lpsProjectName = lpsProjectName;
	}

	public String getLpsStatus() {
		return lpsStatus;
	}

	public void setLpsStatus(String lpsStatus) {
		this.lpsStatus = lpsStatus;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	 
}
