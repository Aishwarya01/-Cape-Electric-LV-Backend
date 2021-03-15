package com.capeelectric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
@Table
@Entity
public class PeriodicDetailsofMainProtective implements Serializable{   //TODO add column name,tablename

	/**
	 * 
	 */
	private static final long serialVersionUID = -6708402291872721257L;

	private Long detailsOfMainProtectiveIDKey;
	private Long installationConditionPrimaryInfoIDKey;
	private String location;
	private String jointno;
	private String jointreResistance;
	private Boolean deletedflg;
	private Timestamp createdDt; 
	private String createdBy; 
	private Timestamp updatedDt; 
	private String updatedBy;
	
	public Long getDetailsOfMainProtectiveIDKey() {
		return detailsOfMainProtectiveIDKey;
	}
	public void setDetailsOfMainProtectiveIDKey(Long detailsOfMainProtectiveIDKey) {
		this.detailsOfMainProtectiveIDKey = detailsOfMainProtectiveIDKey;
	}
	public Long getInstallationConditionPrimaryInfoIDKey() {
		return installationConditionPrimaryInfoIDKey;
	}
	public void setInstallationConditionPrimaryInfoIDKey(Long installationConditionPrimaryInfoIDKey) {
		this.installationConditionPrimaryInfoIDKey = installationConditionPrimaryInfoIDKey;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJointno() {
		return jointno;
	}
	public void setJointno(String jointno) {
		this.jointno = jointno;
	}
	public String getJointreResistance() {
		return jointreResistance;
	}
	public void setJointreResistance(String jointreResistance) {
		this.jointreResistance = jointreResistance;
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
	 
}
