package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.capeelectric.model.ViewerRegister;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "licence_table")
public class Licence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGISTER_ID")
	private Integer licenceId;
	
	@Column(name = "REGISTER_ID")
	private Integer registerId;
	
	//LV
	@Column(name = "NO_OF_LV_LICENCE")
	private String noOfLVLicence;
	
	@Column(name = "SITE_NAME")
	private String siteName;
	
	@Column(name = "LV_STATUS")
	private String lvStatus;
	
	//LPS
	@Column(name = "NO_OF_LPS_LICENCE")
	private String noOfLPSLVLicence;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "CLIENT_NAME")
	private String lpsProjectName;
	
	@Column(name = "LPS_STATUS")
	private String lpsStatus;
	
	//EMC
	@Column(name = "NO_OF_EMC_LICENCE")
	private String noOfEMCLVLicence;
	
	@Column(name = "CLIENT_NAME")
	private String emcClientName;
	
	@Column(name = "EMC_STATUS")
	private String emcStatus;
	
	//RISK
	@Column(name = "NO_OF_RISK_LICENCE")
	private String noOfRISKLVLicence;
	
	@Column(name = "RISK_STATUS")
	private String riskStatus;
	
	@Column(name = "RISK_STATUS")
	private String organisationName;
	
	@Column(name = "RISK_STATUS")
	private String projectName;
	
	//SLD
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "REGISTER_ID")
	private ViewerRegister viewerRegister;

	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
		this.licenceId = licenceId;
	}

	public Integer getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}

	public String getNoOfLVLicence() {
		return noOfLVLicence;
	}

	public void setNoOfLVLicence(String noOfLVLicence) {
		this.noOfLVLicence = noOfLVLicence;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLvStatus() {
		return lvStatus;
	}

	public void setLvStatus(String lvStatus) {
		this.lvStatus = lvStatus;
	}

	public String getNoOfLPSLVLicence() {
		return noOfLPSLVLicence;
	}

	public void setNoOfLPSLVLicence(String noOfLPSLVLicence) {
		this.noOfLPSLVLicence = noOfLPSLVLicence;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public String getNoOfEMCLVLicence() {
		return noOfEMCLVLicence;
	}

	public void setNoOfEMCLVLicence(String noOfEMCLVLicence) {
		this.noOfEMCLVLicence = noOfEMCLVLicence;
	}

	public String getEmcClientName() {
		return emcClientName;
	}

	public void setEmcClientName(String emcClientName) {
		this.emcClientName = emcClientName;
	}

	public String getEmcStatus() {
		return emcStatus;
	}

	public void setEmcStatus(String emcStatus) {
		this.emcStatus = emcStatus;
	}

	public String getNoOfRISKLVLicence() {
		return noOfRISKLVLicence;
	}

	public void setNoOfRISKLVLicence(String noOfRISKLVLicence) {
		this.noOfRISKLVLicence = noOfRISKLVLicence;
	}

	public String getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ViewerRegister getViewerRegister() {
		return viewerRegister;
	}

	public void setViewerRegister(ViewerRegister viewerRegister) {
		this.viewerRegister = viewerRegister;
	}

}
