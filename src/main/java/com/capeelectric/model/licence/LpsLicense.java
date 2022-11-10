package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class LpsLicense extends LicenseTable{

	@Column(name = "LPS_NO_OF_LICENCE")
	private String lpsNoOfLicence;

	@Column(name = "LPS_CLIENT_NAME")
	private String lpsclientName;

	@Column(name = "LPS_PROJECT_NAME")
	private String lpsProjectName;

	@Column(name = "LPS_STATUS")
	private String lpsStatus;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "REGISTER_ID")
	private LpsRegister lpsRegister;

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

	public LpsRegister getLpsRegister() {
		return lpsRegister;
	}

	public void setLpsRegister(LpsRegister lpsRegister) {
		this.lpsRegister = lpsRegister;
	}
}
