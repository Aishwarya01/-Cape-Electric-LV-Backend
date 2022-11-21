package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

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
	
	@Column(name = "VIEWER_USERNAME")
	private String viewerUserName;
	
	@Column(name = "INSPCTOR_USERNAME")
	private String inspectorUserName;
	 

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
