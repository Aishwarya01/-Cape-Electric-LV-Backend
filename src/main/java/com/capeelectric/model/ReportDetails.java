
package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 *
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "REPORT_DETAILS_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "installationReportRepository.retrieveInstallationReport", query = "select r from ReportDetails r where r.userName=:userName") })
public class ReportDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REPORT_ID")
	private Integer reportId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "DESCRIPTION_REPORT")
	private String descriptionReport;

	@Column(name = "REASON_REPORT")
	private String reasonOfReport;

	@Column(name = "INSTALLATION_TYPE")
	private String installationType;

	@Column(name = "DESCRIPTION_PREMISE")
	private String descriptionPremise;

	@Column(name = "ESTIMATED_WIRE_AGE")
	private String estimatedWireAge;

	@Column(name = "EVIDANCE_ADDITION")
	private String evidanceAddition;

	@Column(name = "PREVIOUS_RECORDS")
	private String previousRecords;

	@Column(name = "LAST_INSPECTION")
	private String lastInspection;

	@Column(name = "NEXT_INSPECTION")
	private String nextInspection;

	@Column(name = "EXTENT_INSTALLATION")
	private String extentInstallation;

	@Column(name = "CLIENT_DETAILS")
	private String clientDetails;

	@Column(name = "INSTALLATION_DETAILS")
	private String installationDetails;

	@Column(name = "VERFICATION_DATE")
	private String verficationDate;

	@Column(name = "VERIFED_ENGINEER")
	private String verifedEngineer;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "reportDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SignatorDetails> SignatorDetails;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescriptionReport() {
		return descriptionReport;
	}

	public void setDescriptionReport(String descriptionReport) {
		this.descriptionReport = descriptionReport;
	}

	public String getReasonOfReport() {
		return reasonOfReport;
	}

	public void setReasonOfReport(String reasonOfReport) {
		this.reasonOfReport = reasonOfReport;
	}

	public String getInstallationType() {
		return installationType;
	}

	public void setInstallationType(String installationType) {
		this.installationType = installationType;
	}

	public String getDescriptionPremise() {
		return descriptionPremise;
	}

	public void setDescriptionPremise(String descriptionPremise) {
		this.descriptionPremise = descriptionPremise;
	}

	public String getEstimatedWireAge() {
		return estimatedWireAge;
	}

	public void setEstimatedWireAge(String estimatedWireAge) {
		this.estimatedWireAge = estimatedWireAge;
	}

	public String getEvidanceAddition() {
		return evidanceAddition;
	}

	public void setEvidanceAddition(String evidanceAddition) {
		this.evidanceAddition = evidanceAddition;
	}

	public String getPreviousRecords() {
		return previousRecords;
	}

	public void setPreviousRecords(String previousRecords) {
		this.previousRecords = previousRecords;
	}

	public String getLastInspection() {
		return lastInspection;
	}

	public void setLastInspection(String lastInspection) {
		this.lastInspection = lastInspection;
	}

	public String getNextInspection() {
		return nextInspection;
	}

	public void setNextInspection(String nextInspection) {
		this.nextInspection = nextInspection;
	}

	public String getExtentInstallation() {
		return extentInstallation;
	}

	public void setExtentInstallation(String extentInstallation) {
		this.extentInstallation = extentInstallation;
	}

	public String getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(String clientDetails) {
		this.clientDetails = clientDetails;
	}

	public String getInstallationDetails() {
		return installationDetails;
	}

	public void setInstallationDetails(String installationDetails) {
		this.installationDetails = installationDetails;
	}

	public String getVerficationDate() {
		return verficationDate;
	}

	public void setVerficationDate(String verficationDate) {
		this.verficationDate = verficationDate;
	}

	public String getVerifedEngineer() {
		return verifedEngineer;
	}

	public void setVerifedEngineer(String verifedEngineer) {
		this.verifedEngineer = verifedEngineer;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Set<SignatorDetails> getSignatorDetails() {
		return SignatorDetails;
	}

	public void setSignatorDetails(Set<SignatorDetails> signatorDetails) {
		SignatorDetails = signatorDetails;
	}

}
