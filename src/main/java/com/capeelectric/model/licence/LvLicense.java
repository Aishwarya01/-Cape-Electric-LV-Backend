package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "licence_table")
public class LvLicense extends LicenseTable {

	@Column(name = "LV_NO_OF_LICENCE")
	private String lvNoOfLicence;
	
	@Column(name = "VIEWER_USERNAME")
	private String viewerUserName;
	
	@Column(name = "INSPCTOR_USERNAME")
	private String inspectorUserName;

	public String getLvNoOfLicence() {
		return lvNoOfLicence;
	}

	public void setLvNoOfLicence(String lvNoOfLicence) {
		this.lvNoOfLicence = lvNoOfLicence;
	}

	public String getViewerUserName() {
		return viewerUserName;
	}

	public void setViewerUserName(String viewerUserName) {
		this.viewerUserName = viewerUserName;
	}

	public String getInspectorUserName() {
		return inspectorUserName;
	}

	public void setInspectorUserName(String inspectorUserName) {
		this.inspectorUserName = inspectorUserName;
	}
	 
}
