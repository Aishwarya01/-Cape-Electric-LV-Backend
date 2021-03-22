package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ni_patriclars_installation_addlocation database table.
 * 
 */
@Entity
@Table(name="ni_patriclars_installation_addlocation")
@NamedQuery(name="NiPatriclarsInstallationAddlocation.findAll", query="SELECT n FROM NiPatriclarsInstallationAddlocation n")
public class NiPatriclarsInstallationAddlocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOCATION_ID")
	private int locationId;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="ELECTRODE_RESISTANCE_EARTH")
	private String electrodeResistanceEarth;

	@Column(name="ELECTRODE_RESISTANCE_GRID")
	private String electrodeResistanceGrid;

	@Column(name="LOCATION_NAME")
	private String locationName;

	@Column(name="LOCATION_NO")
	private String locationNo;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional one-to-one association to NiPatriclarsInstallationTable
	@OneToOne
	@JoinColumn(name="LOCATION_ID")
	private NiPatriclarsInstallationTable niPatriclarsInstallationTable;

	public NiPatriclarsInstallationAddlocation() {
	}

	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getElectrodeResistanceEarth() {
		return this.electrodeResistanceEarth;
	}

	public void setElectrodeResistanceEarth(String electrodeResistanceEarth) {
		this.electrodeResistanceEarth = electrodeResistanceEarth;
	}

	public String getElectrodeResistanceGrid() {
		return this.electrodeResistanceGrid;
	}

	public void setElectrodeResistanceGrid(String electrodeResistanceGrid) {
		this.electrodeResistanceGrid = electrodeResistanceGrid;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationNo() {
		return this.locationNo;
	}

	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdateddt() {
		return this.updateddt;
	}

	public void setUpdateddt(Date updateddt) {
		this.updateddt = updateddt;
	}

	public NiPatriclarsInstallationTable getNiPatriclarsInstallationTable() {
		return this.niPatriclarsInstallationTable;
	}

	public void setNiPatriclarsInstallationTable(NiPatriclarsInstallationTable niPatriclarsInstallationTable) {
		this.niPatriclarsInstallationTable = niPatriclarsInstallationTable;
	}

}