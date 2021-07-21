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
@Table(name = "installation_location_table")
public class InstalLocationReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSTALLATION_LOCATION_REPORT_ID")
	private Integer locationReportId;

	@Column(name = "LOCATION_NO")
	private String locationNo;

	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "ELECTORDE_RESISTANCE_EARTH")
	private String electrodeResistanceEarth;

	@Column(name = "ELECTORDE_RESISTANCE_GRID")
	private String electrodeResistanceGird;

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

	public SupplyCharacteristics getSupplyCharacteristics() {
		return supplyCharacteristics;
	}

	public void setSupplyCharacteristics(SupplyCharacteristics supplyCharacteristics) {
		this.supplyCharacteristics = supplyCharacteristics;
	}

}