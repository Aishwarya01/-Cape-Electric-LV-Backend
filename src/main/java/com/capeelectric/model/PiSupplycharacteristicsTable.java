package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pi_supplycharacteristics_table database table.
 * 
 */
@Entity
@Table(name="pi_supplycharacteristics_table")
@NamedQuery(name="PiSupplycharacteristicsTable.findAll", query="SELECT p FROM PiSupplycharacteristicsTable p")
public class PiSupplycharacteristicsTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUPPLYCHARACTERISTICS_KEY")
	private int supplycharacteristicsKey;

	@Column(name="ALTERNATIVE_SOURCE_SUPPLY")
	private byte alternativeSourceSupply;

	@Column(name="EXTERNAL_LOOP")
	private String externalLoop;

	private byte it;

	@Column(name="NOMINAL_FREQUENCY")
	private String nominalFrequency;

	@Column(name="NOMINAL_VOLTAGE")
	private String nominalVoltage;

	private String others;

	@Column(name="OVER_CURRENT")
	private String overCurrent;

	@Column(name="PHASE_1_WIRE_2")
	private byte phase1Wire2;

	@Column(name="PHASE_1_WIRE_3")
	private byte phase1Wire3;

	@Column(name="PHASE_2_WIRE_3")
	private byte phase2Wire3;

	@Column(name="PHASE_3_WIRE_3")
	private byte phase3Wire3;

	@Column(name="PHASE_3_WIRE_4")
	private byte phase3Wire4;

	@Column(name="POLE_2")
	private byte pole2;

	@Column(name="POLE_3")
	private byte pole3;

	@Column(name="PROSPECTIVE_FAULT_CURRENT")
	private String prospectiveFaultCurrent;

	@Column(name="RATED_CURRNT")
	private String ratedCurrnt;

	@Column(name="TN_C")
	private byte tnC;

	@Column(name="TN_C_S")
	private byte tnCS;

	@Column(name="TN_S")
	private byte tnS;

	@Column(name="TO_BE_VERIFIED")
	private byte toBeVerified;

	private byte tt;

	public PiSupplycharacteristicsTable() {
	}

	public int getSupplycharacteristicsKey() {
		return this.supplycharacteristicsKey;
	}

	public void setSupplycharacteristicsKey(int supplycharacteristicsKey) {
		this.supplycharacteristicsKey = supplycharacteristicsKey;
	}

	public byte getAlternativeSourceSupply() {
		return this.alternativeSourceSupply;
	}

	public void setAlternativeSourceSupply(byte alternativeSourceSupply) {
		this.alternativeSourceSupply = alternativeSourceSupply;
	}

	public String getExternalLoop() {
		return this.externalLoop;
	}

	public void setExternalLoop(String externalLoop) {
		this.externalLoop = externalLoop;
	}

	public byte getIt() {
		return this.it;
	}

	public void setIt(byte it) {
		this.it = it;
	}

	public String getNominalFrequency() {
		return this.nominalFrequency;
	}

	public void setNominalFrequency(String nominalFrequency) {
		this.nominalFrequency = nominalFrequency;
	}

	public String getNominalVoltage() {
		return this.nominalVoltage;
	}

	public void setNominalVoltage(String nominalVoltage) {
		this.nominalVoltage = nominalVoltage;
	}

	public String getOthers() {
		return this.others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getOverCurrent() {
		return this.overCurrent;
	}

	public void setOverCurrent(String overCurrent) {
		this.overCurrent = overCurrent;
	}

	public byte getPhase1Wire2() {
		return this.phase1Wire2;
	}

	public void setPhase1Wire2(byte phase1Wire2) {
		this.phase1Wire2 = phase1Wire2;
	}

	public byte getPhase1Wire3() {
		return this.phase1Wire3;
	}

	public void setPhase1Wire3(byte phase1Wire3) {
		this.phase1Wire3 = phase1Wire3;
	}

	public byte getPhase2Wire3() {
		return this.phase2Wire3;
	}

	public void setPhase2Wire3(byte phase2Wire3) {
		this.phase2Wire3 = phase2Wire3;
	}

	public byte getPhase3Wire3() {
		return this.phase3Wire3;
	}

	public void setPhase3Wire3(byte phase3Wire3) {
		this.phase3Wire3 = phase3Wire3;
	}

	public byte getPhase3Wire4() {
		return this.phase3Wire4;
	}

	public void setPhase3Wire4(byte phase3Wire4) {
		this.phase3Wire4 = phase3Wire4;
	}

	public byte getPole2() {
		return this.pole2;
	}

	public void setPole2(byte pole2) {
		this.pole2 = pole2;
	}

	public byte getPole3() {
		return this.pole3;
	}

	public void setPole3(byte pole3) {
		this.pole3 = pole3;
	}

	public String getProspectiveFaultCurrent() {
		return this.prospectiveFaultCurrent;
	}

	public void setProspectiveFaultCurrent(String prospectiveFaultCurrent) {
		this.prospectiveFaultCurrent = prospectiveFaultCurrent;
	}

	public String getRatedCurrnt() {
		return this.ratedCurrnt;
	}

	public void setRatedCurrnt(String ratedCurrnt) {
		this.ratedCurrnt = ratedCurrnt;
	}

	public byte getTnC() {
		return this.tnC;
	}

	public void setTnC(byte tnC) {
		this.tnC = tnC;
	}

	public byte getTnCS() {
		return this.tnCS;
	}

	public void setTnCS(byte tnCS) {
		this.tnCS = tnCS;
	}

	public byte getTnS() {
		return this.tnS;
	}

	public void setTnS(byte tnS) {
		this.tnS = tnS;
	}

	public byte getToBeVerified() {
		return this.toBeVerified;
	}

	public void setToBeVerified(byte toBeVerified) {
		this.toBeVerified = toBeVerified;
	}

	public byte getTt() {
		return this.tt;
	}

	public void setTt(byte tt) {
		this.tt = tt;
	}

}