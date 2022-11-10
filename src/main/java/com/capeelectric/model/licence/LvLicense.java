package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "licence_table")
public class LvLicense extends LicenseTable {

	@Column(name = "LV_SITE_NAME")
	private String lvSiteName;

	@Column(name = "LV_STATUS")
	private String lvStatus;

	@Column(name = "LV_NO_OF_LICENCE")
	private String lvNoOfLicence;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "REGISTER_ID")
	private LvRegister lvRegister;

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

	public String getLvNoOfLicence() {
		return lvNoOfLicence;
	}

	public void setLvNoOfLicence(String lvNoOfLicence) {
		this.lvNoOfLicence = lvNoOfLicence;
	}

	public LvRegister getLvRegister() {
		return lvRegister;
	}

	public void setLvRegister(LvRegister lvRegister) {
		this.lvRegister = lvRegister;
	}

}
