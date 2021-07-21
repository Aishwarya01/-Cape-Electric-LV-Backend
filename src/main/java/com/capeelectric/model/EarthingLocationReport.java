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

/**
*
* @author capeelectricsoftware
*
*/
@Entity
@Table(name = "earthing_location_reports_table")
public class EarthingLocationReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EARTHING_LOCATION_REPORT_ID")
	private Integer locationReportId;
 
	@Column(name = "LOCATION")
	private String location;

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
