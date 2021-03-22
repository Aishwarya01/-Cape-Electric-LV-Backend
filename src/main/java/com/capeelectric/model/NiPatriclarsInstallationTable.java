package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ni_patriclars_installation_table database table.
 * 
 */
@Entity
@Table(name="ni_patriclars_installation_table")
@NamedQuery(name="NiPatriclarsInstallationTable.findAll", query="SELECT n FROM NiPatriclarsInstallationTable n")
public class NiPatriclarsInstallationTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INSTALLATION_KEY")
	private int installationKey;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="INSTALLATION_EARTH_ELETRODE")
	private byte installationEarthEletrode;

	@Column(name="MATERIAL_COPPER")
	private byte materialCopper;

	@Column(name="MATERIAL_IRON")
	private byte materialIron;

	@Column(name="MATERIAL_OTHERS")
	private String materialOthers;

	@Column(name="MAXIMUM_DEMAND")
	private String maximumDemand;

	@Column(name="MAXIMUM_LOAD")
	private String maximumLoad;

	@Column(name="NO_OF_LOCATIONS")
	private String noOfLocations;

	@Column(name="SUPPLIERS_FACILITY")
	private byte suppliersFacility;

	@Column(name="TYPES_OTHERS")
	private String typesOthers;

	@Column(name="TYPES_ROD")
	private byte typesRod;

	@Column(name="TYPES_TAPE")
	private byte typesTape;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional one-to-one association to NiPatriclarsInstallationAddlocation
	@OneToOne(mappedBy="niPatriclarsInstallationTable")
	private NiPatriclarsInstallationAddlocation niPatriclarsInstallationAddlocation;

	public NiPatriclarsInstallationTable() {
	}

	public int getInstallationKey() {
		return this.installationKey;
	}

	public void setInstallationKey(int installationKey) {
		this.installationKey = installationKey;
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

	public byte getInstallationEarthEletrode() {
		return this.installationEarthEletrode;
	}

	public void setInstallationEarthEletrode(byte installationEarthEletrode) {
		this.installationEarthEletrode = installationEarthEletrode;
	}

	public byte getMaterialCopper() {
		return this.materialCopper;
	}

	public void setMaterialCopper(byte materialCopper) {
		this.materialCopper = materialCopper;
	}

	public byte getMaterialIron() {
		return this.materialIron;
	}

	public void setMaterialIron(byte materialIron) {
		this.materialIron = materialIron;
	}

	public String getMaterialOthers() {
		return this.materialOthers;
	}

	public void setMaterialOthers(String materialOthers) {
		this.materialOthers = materialOthers;
	}

	public String getMaximumDemand() {
		return this.maximumDemand;
	}

	public void setMaximumDemand(String maximumDemand) {
		this.maximumDemand = maximumDemand;
	}

	public String getMaximumLoad() {
		return this.maximumLoad;
	}

	public void setMaximumLoad(String maximumLoad) {
		this.maximumLoad = maximumLoad;
	}

	public String getNoOfLocations() {
		return this.noOfLocations;
	}

	public void setNoOfLocations(String noOfLocations) {
		this.noOfLocations = noOfLocations;
	}

	public byte getSuppliersFacility() {
		return this.suppliersFacility;
	}

	public void setSuppliersFacility(byte suppliersFacility) {
		this.suppliersFacility = suppliersFacility;
	}

	public String getTypesOthers() {
		return this.typesOthers;
	}

	public void setTypesOthers(String typesOthers) {
		this.typesOthers = typesOthers;
	}

	public byte getTypesRod() {
		return this.typesRod;
	}

	public void setTypesRod(byte typesRod) {
		this.typesRod = typesRod;
	}

	public byte getTypesTape() {
		return this.typesTape;
	}

	public void setTypesTape(byte typesTape) {
		this.typesTape = typesTape;
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

	public NiPatriclarsInstallationAddlocation getNiPatriclarsInstallationAddlocation() {
		return this.niPatriclarsInstallationAddlocation;
	}

	public void setNiPatriclarsInstallationAddlocation(NiPatriclarsInstallationAddlocation niPatriclarsInstallationAddlocation) {
		this.niPatriclarsInstallationAddlocation = niPatriclarsInstallationAddlocation;
	}

}