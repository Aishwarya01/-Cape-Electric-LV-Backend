package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class LvLicence extends LicenceTable{

	@Column(name = "SITE_NAME")
	private String siteName;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "REGISTER_ID")
	private LvRegister lvRegister;

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public LvRegister getLvRegister() {
		return lvRegister;
	}

	public void setLvRegister(LvRegister lvRegister) {
		this.lvRegister = lvRegister;
	}
	
}
