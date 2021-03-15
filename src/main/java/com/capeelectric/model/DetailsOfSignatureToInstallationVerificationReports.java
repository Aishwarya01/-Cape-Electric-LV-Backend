package com.capeelectric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
@Table
@Entity
public class DetailsOfSignatureToInstallationVerificationReports implements Serializable {
                                 
	                              // TODDO add column name, table name
	/**
	 * 
	 */
	private static final long serialVersionUID = 4313771962351031075L;
	private Long installationVerificationReportsIDKey;
	private Long clientIDKey;
	private String clientEmail;
	private Long departmentIDKey;
	private Long siteIDKey;
	private String descriptionofInstall;
	private String extentofInstallation;
	private String siteLocation;
	private Integer newInstallation;
	private String nextInstallation;
	private String designer1Name;
	private String designer1ContactNo;
	private String designer1EmailID;
	private String designer1ManagerName;
	private String designer1ManagerContactNo;
	private String designer1ManagerEmail;
	private String designer1Company;
	private String designer1AddressLine1;
	private String designer1AddressLine2;
	private String designer1Landmark;
	private String designer1State;
	private String designer1PostCode;
	private String designer2Name;
	private String designer2ContactNo;
	private String designer2Email;
	private String constructorName;
	private String constructorContactNo;
	private String constructorEmail;
	private String constructorManagerName;
	private String constructorManagerContactNo;
	private String constructorManagerEmail;
	private String constructorCompany;
	private String constructorAddressLine1;
	private String constructorAddressLine2;
	private String constructorLandmark;
	private String constructorState;
	private String constructorPostCode;
	private String inspectorName;
	private String inspectorContactNo;
	private String inspectorEmailID;
	private String inspectorManagerName;
	private String inspectorManagerContactNo;
	private String inspectorManagerEmailID;
	private String inspectorCompany;
	private String inspectorAddressLine1;
	private String inspectorAddressLine2;
	private String inspectorLandmark;
	private String inspectorState;
	private String inspectorPostCode;
	private Boolean meaningOfEarthing;
	private String maximumDemand;
	private String maximumLoad;
	private String noofLocations;
	private Integer typesOfEarthElectrode;
	private Integer materialOfEarthElectrode;
	private String sizeOfEarthingConductor;
	private String materialOfEarthingConductor;
	private String sizeOfMainProtective;
	private String materialOfMainProtective;
	private String typesOfJoints;
	private String noOfJoints;
	private Boolean earthingConductorContinuity;
	private Boolean mainProtectiveConductorContinuity;
	private String location;
	private String type;
	private String noOfPoles;
	private String currentRating;
	private String voltageRating;
	private String fuseRatingOrSettings;
	private String ratedResidualOperatingCurrent;
	private String ratedResidualOperatingTime;
	private String userIDkey;
	private Timestamp createdDt;
	private String createdBy;
	private Timestamp updatedDt;
	private String updatedBy;
	private String reportType;
	private String designerReportName;
	private String constructionReportName;
	private String inspectionName;
	private Timestamp inspectionDate;
	private Boolean deleteflg;
	public Long getInstallationVerificationReportsIDKey() {
		return installationVerificationReportsIDKey;
	}
	public void setInstallationVerificationReportsIDKey(Long installationVerificationReportsIDKey) {
		this.installationVerificationReportsIDKey = installationVerificationReportsIDKey;
	}
	public Long getClientIDKey() {
		return clientIDKey;
	}
	public void setClientIDKey(Long clientIDKey) {
		this.clientIDKey = clientIDKey;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public Long getDepartmentIDKey() {
		return departmentIDKey;
	}
	public void setDepartmentIDKey(Long departmentIDKey) {
		this.departmentIDKey = departmentIDKey;
	}
	public Long getSiteIDKey() {
		return siteIDKey;
	}
	public void setSiteIDKey(Long siteIDKey) {
		this.siteIDKey = siteIDKey;
	}
	public String getDescriptionofInstall() {
		return descriptionofInstall;
	}
	public void setDescriptionofInstall(String descriptionofInstall) {
		this.descriptionofInstall = descriptionofInstall;
	}
	public String getExtentofInstallation() {
		return extentofInstallation;
	}
	public void setExtentofInstallation(String extentofInstallation) {
		this.extentofInstallation = extentofInstallation;
	}
	public String getSiteLocation() {
		return siteLocation;
	}
	public void setSiteLocation(String siteLocation) {
		this.siteLocation = siteLocation;
	}
	public Integer getNewInstallation() {
		return newInstallation;
	}
	public void setNewInstallation(Integer newInstallation) {
		this.newInstallation = newInstallation;
	}
	public String getNextInstallation() {
		return nextInstallation;
	}
	public void setNextInstallation(String nextInstallation) {
		this.nextInstallation = nextInstallation;
	}
	public String getDesigner1Name() {
		return designer1Name;
	}
	public void setDesigner1Name(String designer1Name) {
		this.designer1Name = designer1Name;
	}
	public String getDesigner1ContactNo() {
		return designer1ContactNo;
	}
	public void setDesigner1ContactNo(String designer1ContactNo) {
		this.designer1ContactNo = designer1ContactNo;
	}
	public String getDesigner1EmailID() {
		return designer1EmailID;
	}
	public void setDesigner1EmailID(String designer1EmailID) {
		this.designer1EmailID = designer1EmailID;
	}
	public String getDesigner1ManagerName() {
		return designer1ManagerName;
	}
	public void setDesigner1ManagerName(String designer1ManagerName) {
		this.designer1ManagerName = designer1ManagerName;
	}
	public String getDesigner1ManagerContactNo() {
		return designer1ManagerContactNo;
	}
	public void setDesigner1ManagerContactNo(String designer1ManagerContactNo) {
		this.designer1ManagerContactNo = designer1ManagerContactNo;
	}
	public String getDesigner1ManagerEmail() {
		return designer1ManagerEmail;
	}
	public void setDesigner1ManagerEmail(String designer1ManagerEmail) {
		this.designer1ManagerEmail = designer1ManagerEmail;
	}
	public String getDesigner1Company() {
		return designer1Company;
	}
	public void setDesigner1Company(String designer1Company) {
		this.designer1Company = designer1Company;
	}
	public String getDesigner1AddressLine1() {
		return designer1AddressLine1;
	}
	public void setDesigner1AddressLine1(String designer1AddressLine1) {
		this.designer1AddressLine1 = designer1AddressLine1;
	}
	public String getDesigner1AddressLine2() {
		return designer1AddressLine2;
	}
	public void setDesigner1AddressLine2(String designer1AddressLine2) {
		this.designer1AddressLine2 = designer1AddressLine2;
	}
	public String getDesigner1Landmark() {
		return designer1Landmark;
	}
	public void setDesigner1Landmark(String designer1Landmark) {
		this.designer1Landmark = designer1Landmark;
	}
	public String getDesigner1State() {
		return designer1State;
	}
	public void setDesigner1State(String designer1State) {
		this.designer1State = designer1State;
	}
	public String getDesigner1PostCode() {
		return designer1PostCode;
	}
	public void setDesigner1PostCode(String designer1PostCode) {
		this.designer1PostCode = designer1PostCode;
	}
	public String getDesigner2Name() {
		return designer2Name;
	}
	public void setDesigner2Name(String designer2Name) {
		this.designer2Name = designer2Name;
	}
	public String getDesigner2ContactNo() {
		return designer2ContactNo;
	}
	public void setDesigner2ContactNo(String designer2ContactNo) {
		this.designer2ContactNo = designer2ContactNo;
	}
	public String getDesigner2Email() {
		return designer2Email;
	}
	public void setDesigner2Email(String designer2Email) {
		this.designer2Email = designer2Email;
	}
	public String getConstructorName() {
		return constructorName;
	}
	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}
	public String getConstructorContactNo() {
		return constructorContactNo;
	}
	public void setConstructorContactNo(String constructorContactNo) {
		this.constructorContactNo = constructorContactNo;
	}
	public String getConstructorEmail() {
		return constructorEmail;
	}
	public void setConstructorEmail(String constructorEmail) {
		this.constructorEmail = constructorEmail;
	}
	public String getConstructorManagerName() {
		return constructorManagerName;
	}
	public void setConstructorManagerName(String constructorManagerName) {
		this.constructorManagerName = constructorManagerName;
	}
	public String getConstructorManagerContactNo() {
		return constructorManagerContactNo;
	}
	public void setConstructorManagerContactNo(String constructorManagerContactNo) {
		this.constructorManagerContactNo = constructorManagerContactNo;
	}
	public String getConstructorManagerEmail() {
		return constructorManagerEmail;
	}
	public void setConstructorManagerEmail(String constructorManagerEmail) {
		this.constructorManagerEmail = constructorManagerEmail;
	}
	public String getConstructorCompany() {
		return constructorCompany;
	}
	public void setConstructorCompany(String constructorCompany) {
		this.constructorCompany = constructorCompany;
	}
	public String getConstructorAddressLine1() {
		return constructorAddressLine1;
	}
	public void setConstructorAddressLine1(String constructorAddressLine1) {
		this.constructorAddressLine1 = constructorAddressLine1;
	}
	public String getConstructorAddressLine2() {
		return constructorAddressLine2;
	}
	public void setConstructorAddressLine2(String constructorAddressLine2) {
		this.constructorAddressLine2 = constructorAddressLine2;
	}
	public String getConstructorLandmark() {
		return constructorLandmark;
	}
	public void setConstructorLandmark(String constructorLandmark) {
		this.constructorLandmark = constructorLandmark;
	}
	public String getConstructorState() {
		return constructorState;
	}
	public void setConstructorState(String constructorState) {
		this.constructorState = constructorState;
	}
	public String getConstructorPostCode() {
		return constructorPostCode;
	}
	public void setConstructorPostCode(String constructorPostCode) {
		this.constructorPostCode = constructorPostCode;
	}
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	public String getInspectorContactNo() {
		return inspectorContactNo;
	}
	public void setInspectorContactNo(String inspectorContactNo) {
		this.inspectorContactNo = inspectorContactNo;
	}
	public String getInspectorEmailID() {
		return inspectorEmailID;
	}
	public void setInspectorEmailID(String inspectorEmailID) {
		this.inspectorEmailID = inspectorEmailID;
	}
	public String getInspectorManagerName() {
		return inspectorManagerName;
	}
	public void setInspectorManagerName(String inspectorManagerName) {
		this.inspectorManagerName = inspectorManagerName;
	}
	public String getInspectorManagerContactNo() {
		return inspectorManagerContactNo;
	}
	public void setInspectorManagerContactNo(String inspectorManagerContactNo) {
		this.inspectorManagerContactNo = inspectorManagerContactNo;
	}
	public String getInspectorManagerEmailID() {
		return inspectorManagerEmailID;
	}
	public void setInspectorManagerEmailID(String inspectorManagerEmailID) {
		this.inspectorManagerEmailID = inspectorManagerEmailID;
	}
	public String getInspectorCompany() {
		return inspectorCompany;
	}
	public void setInspectorCompany(String inspectorCompany) {
		this.inspectorCompany = inspectorCompany;
	}
	public String getInspectorAddressLine1() {
		return inspectorAddressLine1;
	}
	public void setInspectorAddressLine1(String inspectorAddressLine1) {
		this.inspectorAddressLine1 = inspectorAddressLine1;
	}
	public String getInspectorAddressLine2() {
		return inspectorAddressLine2;
	}
	public void setInspectorAddressLine2(String inspectorAddressLine2) {
		this.inspectorAddressLine2 = inspectorAddressLine2;
	}
	public String getInspectorLandmark() {
		return inspectorLandmark;
	}
	public void setInspectorLandmark(String inspectorLandmark) {
		this.inspectorLandmark = inspectorLandmark;
	}
	public String getInspectorState() {
		return inspectorState;
	}
	public void setInspectorState(String inspectorState) {
		this.inspectorState = inspectorState;
	}
	public String getInspectorPostCode() {
		return inspectorPostCode;
	}
	public void setInspectorPostCode(String inspectorPostCode) {
		this.inspectorPostCode = inspectorPostCode;
	}
	public Boolean getMeaningOfEarthing() {
		return meaningOfEarthing;
	}
	public void setMeaningOfEarthing(Boolean meaningOfEarthing) {
		this.meaningOfEarthing = meaningOfEarthing;
	}
	public String getMaximumDemand() {
		return maximumDemand;
	}
	public void setMaximumDemand(String maximumDemand) {
		this.maximumDemand = maximumDemand;
	}
	public String getMaximumLoad() {
		return maximumLoad;
	}
	public void setMaximumLoad(String maximumLoad) {
		this.maximumLoad = maximumLoad;
	}
	public String getNoofLocations() {
		return noofLocations;
	}
	public void setNoofLocations(String noofLocations) {
		this.noofLocations = noofLocations;
	}
	public Integer getTypesOfEarthElectrode() {
		return typesOfEarthElectrode;
	}
	public void setTypesOfEarthElectrode(Integer typesOfEarthElectrode) {
		this.typesOfEarthElectrode = typesOfEarthElectrode;
	}
	public Integer getMaterialOfEarthElectrode() {
		return materialOfEarthElectrode;
	}
	public void setMaterialOfEarthElectrode(Integer materialOfEarthElectrode) {
		this.materialOfEarthElectrode = materialOfEarthElectrode;
	}
	public String getSizeOfEarthingConductor() {
		return sizeOfEarthingConductor;
	}
	public void setSizeOfEarthingConductor(String sizeOfEarthingConductor) {
		this.sizeOfEarthingConductor = sizeOfEarthingConductor;
	}
	public String getMaterialOfEarthingConductor() {
		return materialOfEarthingConductor;
	}
	public void setMaterialOfEarthingConductor(String materialOfEarthingConductor) {
		this.materialOfEarthingConductor = materialOfEarthingConductor;
	}
	public String getSizeOfMainProtective() {
		return sizeOfMainProtective;
	}
	public void setSizeOfMainProtective(String sizeOfMainProtective) {
		this.sizeOfMainProtective = sizeOfMainProtective;
	}
	public String getMaterialOfMainProtective() {
		return materialOfMainProtective;
	}
	public void setMaterialOfMainProtective(String materialOfMainProtective) {
		this.materialOfMainProtective = materialOfMainProtective;
	}
	public String getTypesOfJoints() {
		return typesOfJoints;
	}
	public void setTypesOfJoints(String typesOfJoints) {
		this.typesOfJoints = typesOfJoints;
	}
	public String getNoOfJoints() {
		return noOfJoints;
	}
	public void setNoOfJoints(String noOfJoints) {
		this.noOfJoints = noOfJoints;
	}
	public Boolean getEarthingConductorContinuity() {
		return earthingConductorContinuity;
	}
	public void setEarthingConductorContinuity(Boolean earthingConductorContinuity) {
		this.earthingConductorContinuity = earthingConductorContinuity;
	}
	public Boolean getMainProtectiveConductorContinuity() {
		return mainProtectiveConductorContinuity;
	}
	public void setMainProtectiveConductorContinuity(Boolean mainProtectiveConductorContinuity) {
		this.mainProtectiveConductorContinuity = mainProtectiveConductorContinuity;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNoOfPoles() {
		return noOfPoles;
	}
	public void setNoOfPoles(String noOfPoles) {
		this.noOfPoles = noOfPoles;
	}
	public String getCurrentRating() {
		return currentRating;
	}
	public void setCurrentRating(String currentRating) {
		this.currentRating = currentRating;
	}
	public String getVoltageRating() {
		return voltageRating;
	}
	public void setVoltageRating(String voltageRating) {
		this.voltageRating = voltageRating;
	}
	public String getFuseRatingOrSettings() {
		return fuseRatingOrSettings;
	}
	public void setFuseRatingOrSettings(String fuseRatingOrSettings) {
		this.fuseRatingOrSettings = fuseRatingOrSettings;
	}
	public String getRatedResidualOperatingCurrent() {
		return ratedResidualOperatingCurrent;
	}
	public void setRatedResidualOperatingCurrent(String ratedResidualOperatingCurrent) {
		this.ratedResidualOperatingCurrent = ratedResidualOperatingCurrent;
	}
	public String getRatedResidualOperatingTime() {
		return ratedResidualOperatingTime;
	}
	public void setRatedResidualOperatingTime(String ratedResidualOperatingTime) {
		this.ratedResidualOperatingTime = ratedResidualOperatingTime;
	}
	public String getUserIDkey() {
		return userIDkey;
	}
	public void setUserIDkey(String userIDkey) {
		this.userIDkey = userIDkey;
	}
	public Timestamp getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getUpdatedDt() {
		return updatedDt;
	}
	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getDesignerReportName() {
		return designerReportName;
	}
	public void setDesignerReportName(String designerReportName) {
		this.designerReportName = designerReportName;
	}
	public String getConstructionReportName() {
		return constructionReportName;
	}
	public void setConstructionReportName(String constructionReportName) {
		this.constructionReportName = constructionReportName;
	}
	public String getInspectionName() {
		return inspectionName;
	}
	public void setInspectionName(String inspectionName) {
		this.inspectionName = inspectionName;
	}
	public Timestamp getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Timestamp inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public Boolean getDeleteflg() {
		return deleteflg;
	}
	public void setDeleteflg(Boolean deleteflg) {
		this.deleteflg = deleteflg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
