package com.capeelectric.entity;

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

	private Long DetailsofMainProtectiveIDKey;
	private Long InstallationConditionPrimaryInfoIDKey;
	private String LOCATION;
	private String JOINTNO;
	private String JOINTRESISTANCE;
	private Boolean deletedflg;
	private Timestamp CreatedDt; 
	private String CreatedBy; 
	private Timestamp UpdatedDt; 
	private String UpdatedBy;
	
	public Long getDetailsofMainProtectiveIDKey() {
		return DetailsofMainProtectiveIDKey;
	}
	public void setDetailsofMainProtectiveIDKey(Long detailsofMainProtectiveIDKey) {
		DetailsofMainProtectiveIDKey = detailsofMainProtectiveIDKey;
	}
	public Long getInstallationConditionPrimaryInfoIDKey() {
		return InstallationConditionPrimaryInfoIDKey;
	}
	public void setInstallationConditionPrimaryInfoIDKey(Long installationConditionPrimaryInfoIDKey) {
		InstallationConditionPrimaryInfoIDKey = installationConditionPrimaryInfoIDKey;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public String getJOINTNO() {
		return JOINTNO;
	}
	public void setJOINTNO(String jOINTNO) {
		JOINTNO = jOINTNO;
	}
	public String getJOINTRESISTANCE() {
		return JOINTRESISTANCE;
	}
	public void setJOINTRESISTANCE(String jOINTRESISTANCE) {
		JOINTRESISTANCE = jOINTRESISTANCE;
	}
	public Boolean getDeletedflg() {
		return deletedflg;
	}
	public void setDeletedflg(Boolean deletedflg) {
		this.deletedflg = deletedflg;
	}
	public Timestamp getCreatedDt() {
		return CreatedDt;
	}
	public void setCreatedDt(Timestamp createdDt) {
		CreatedDt = createdDt;
	}
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public Timestamp getUpdatedDt() {
		return UpdatedDt;
	}
	public void setUpdatedDt(Timestamp updatedDt) {
		UpdatedDt = updatedDt;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
}
