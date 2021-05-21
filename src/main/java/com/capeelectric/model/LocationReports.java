package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "LOCATION_REPORTS_TABLE")
public class LocationReports implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOCATION_REPORT_ID")
	private Integer locationReportId;

	@Column(name = "PARTICULAR_INSTAL_OR_PROTECTIVE_CONDCUTOR")
	private String particularInstalOrProtectiveConductor;

	@Column(name = "BOUNDING_OR_EARTHING")
	private String boundingOrEarthing;

	@Column(name = "LOCATION_NO")
	private String locationNo;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "ELECTORDE_RESISTANCE_EARTH")
	private String electrodeResistanceEarth;

	@Column(name = "ELECTORDE_RESISTANCE_GRID")
	private String electrodeResistanceGird;

	@Column(name = "JOINT_NO")
	private String jointNo;

	@Column(name = "JOINT_RESISTANCE")
	private String jointResistance;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "SUPPLY_CHARACTERISTICS_ID")
	private SupplyCharacteristics supplyCharacteristics;

	public Integer getLocationReportId() {
		return locationReportId;
	}

	public void setLocationReportId(Integer locationReportId) {
		this.locationReportId = locationReportId;
	}

	public String getParticularInstalOrProtectiveConductor() {
		return particularInstalOrProtectiveConductor;
	}

	public void setParticularInstalOrProtectiveConductor(String particularInstalOrProtectiveConductor) {
		this.particularInstalOrProtectiveConductor = particularInstalOrProtectiveConductor;
	}

	public String getBoundingOrEarthing() {
		return boundingOrEarthing;
	}

	public void setBoundingOrEarthing(String boundingOrEarthing) {
		this.boundingOrEarthing = boundingOrEarthing;
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

	public String getElectrodeResistanceGird() {
		return electrodeResistanceGird;
	}

	public void setElectrodeResistanceGird(String electrodeResistanceGird) {
		this.electrodeResistanceGird = electrodeResistanceGird;
	}

	public String getJointNo() {
		return jointNo;
	}

	public void setJointNo(String jointNo) {
		this.jointNo = jointNo;
	}

	public String getJointResistance() {
		return jointResistance;
	}

	public void setJointResistance(String jointResistance) {
		this.jointResistance = jointResistance;
	}

	public SupplyCharacteristics getSupplyCharacteristics() {
		return supplyCharacteristics;
	}

	public void setSupplyCharacteristics(SupplyCharacteristics supplyCharacteristics) {
		this.supplyCharacteristics = supplyCharacteristics;
	}

}
