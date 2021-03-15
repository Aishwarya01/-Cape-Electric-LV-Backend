package com.capeelectric.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
@Table
@Entity
public class InstallationReferredReport implements Serializable { //TODO column name and table name

	 
	private static final long serialVersionUID = 6370928230434865294L;
    
	private Long particularInstallationIDKey;
	private Long installationConditionPrimaryInfoIDKey;
	private String locationNo;
	private String locationName;
	private String electrodeResistanceEarth;
	private String electrodeResistanceGrid;
	private Boolean deletedflg;
	private Timestamp createdDt;
	private String createdBy;
	private Timestamp updatedDt;
	private String updatedBy;
	
	public Long getParticularInstallationIDKey() {
		return particularInstallationIDKey;
	}
	public void setParticularInstallationIDKey(Long particularInstallationIDKey) {
		this.particularInstallationIDKey = particularInstallationIDKey;
	}
	public Long getInstallationConditionPrimaryInfoIDKey() {
		return installationConditionPrimaryInfoIDKey;
	}
	public void setInstallationConditionPrimaryInfoIDKey(Long installationConditionPrimaryInfoIDKey) {
		this.installationConditionPrimaryInfoIDKey = installationConditionPrimaryInfoIDKey;
	}
	public String getLocationNo() {
		return locationNo;
	}
	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getElectrodeResistanceEarth() {
		return electrodeResistanceEarth;
	}
	public void setElectrodeResistanceEarth(String electrodeResistanceEarth) {
		this.electrodeResistanceEarth = electrodeResistanceEarth;
	}
	public String getElectrodeResistanceGrid() {
		return electrodeResistanceGrid;
	}
	public void setElectrodeResistanceGrid(String electrodeResistanceGrid) {
		this.electrodeResistanceGrid = electrodeResistanceGrid;
	}
	public Boolean getDeletedflg() {
		return deletedflg;
	}
	public void setDeletedflg(Boolean deletedflg) {
		this.deletedflg = deletedflg;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 

}
