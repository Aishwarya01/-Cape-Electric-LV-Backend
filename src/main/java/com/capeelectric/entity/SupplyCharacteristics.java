package com.capeelectric.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class SupplyCharacteristics  implements Serializable {       ////TODO add column name,tablename
	 
	private static final long serialVersionUID = -5702443479573850545L;
	@Id
	private Long supplyCharacteristicsIDKey;
	private Long installationVerificationReportsIDKey;
	private Integer earthingSystemType;
	private Integer numberAndTypeOfLiveConductor;
	private String nominalVoltage;
	private String nominalFrequency;
	private String prospectiveFaultCurrent;
	private String externalLoopImpedance;
	private String overCurrentProtective;
	private String ratedCurrent;
	private Boolean alternativeSourceOfSupply;
	private String userIDkey;
	private Timestamp createdDt;
	private String createdBy;
	private Timestamp updatedDt;
	private String updatedBy;

	public Long getSupplyCharacteristicsIDKey() {
		return supplyCharacteristicsIDKey;
	}

	public void setSupplyCharacteristicsIDKey(Long supplyCharacteristicsIDKey) {
		this.supplyCharacteristicsIDKey = supplyCharacteristicsIDKey;
	}

	public Long getInstallationVerificationReportsIDKey() {
		return installationVerificationReportsIDKey;
	}

	public void setInstallationVerificationReportsIDKey(Long installationVerificationReportsIDKey) {
		this.installationVerificationReportsIDKey = installationVerificationReportsIDKey;
	}

	public Integer getEarthingSystemType() {
		return earthingSystemType;
	}

	public void setEarthingSystemType(Integer earthingSystemType) {
		this.earthingSystemType = earthingSystemType;
	}

	public Integer getNumberAndTypeOfLiveConductor() {
		return numberAndTypeOfLiveConductor;
	}

	public void setNumberAndTypeOfLiveConductor(Integer numberAndTypeOfLiveConductor) {
		this.numberAndTypeOfLiveConductor = numberAndTypeOfLiveConductor;
	}

	public String getNominalVoltage() {
		return nominalVoltage;
	}

	public void setNominalVoltage(String nominalVoltage) {
		this.nominalVoltage = nominalVoltage;
	}

	public String getNominalFrequency() {
		return nominalFrequency;
	}

	public void setNominalFrequency(String nominalFrequency) {
		this.nominalFrequency = nominalFrequency;
	}

	public String getProspectiveFaultCurrent() {
		return prospectiveFaultCurrent;
	}

	public void setProspectiveFaultCurrent(String prospectiveFaultCurrent) {
		this.prospectiveFaultCurrent = prospectiveFaultCurrent;
	}

	public String getExternalLoopImpedance() {
		return externalLoopImpedance;
	}

	public void setExternalLoopImpedance(String externalLoopImpedance) {
		this.externalLoopImpedance = externalLoopImpedance;
	}

	public String getOverCurrentProtective() {
		return overCurrentProtective;
	}

	public void setOverCurrentProtective(String overCurrentProtective) {
		this.overCurrentProtective = overCurrentProtective;
	}

	public String getRatedCurrent() {
		return ratedCurrent;
	}

	public void setRatedCurrent(String ratedCurrent) {
		this.ratedCurrent = ratedCurrent;
	}

	public Boolean getAlternativeSourceOfSupply() {
		return alternativeSourceOfSupply;
	}

	public void setAlternativeSourceOfSupply(Boolean alternativeSourceOfSupply) {
		this.alternativeSourceOfSupply = alternativeSourceOfSupply;
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
}
