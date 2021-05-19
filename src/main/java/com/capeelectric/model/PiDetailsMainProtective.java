package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pi_details_main_protective database table.
 * 
 */
@Entity
@Table(name="pi_details_main_protective")
@NamedQuery(name="PiDetailsMainProtective.findAll", query="SELECT p FROM PiDetailsMainProtective p")
public class PiDetailsMainProtective implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MAIN_PROTECTIVE_KEY")
	private int mainProtectiveKey;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="EARTHING_CONDUCTOR")
	private byte earthingConductor;

	@Column(name="MAIN_PROTECTIVE_CONDUCTOR")
	private byte mainProtectiveConductor;

	@Column(name="MATERIAL_EARTHING_CONDUCTOR")
	private String materialEarthingConductor;

	@Column(name="SIZE_EARTH_CONDUCTOR")
	private String sizeEarthConductor;

	@Column(name="TYPES_JOINTS")
	private String typesJoints;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional one-to-one association to PiDetailsMainProtectiveAddlocation
	@OneToOne(mappedBy="piDetailsMainProtective")
	private PiDetailsMainProtectiveAddlocation piDetailsMainProtectiveAddlocation;

	public PiDetailsMainProtective() {
	}

	public int getMainProtectiveKey() {
		return this.mainProtectiveKey;
	}

	public void setMainProtectiveKey(int mainProtectiveKey) {
		this.mainProtectiveKey = mainProtectiveKey;
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

	public byte getEarthingConductor() {
		return this.earthingConductor;
	}

	public void setEarthingConductor(byte earthingConductor) {
		this.earthingConductor = earthingConductor;
	}

	public byte getMainProtectiveConductor() {
		return this.mainProtectiveConductor;
	}

	public void setMainProtectiveConductor(byte mainProtectiveConductor) {
		this.mainProtectiveConductor = mainProtectiveConductor;
	}

	public String getMaterialEarthingConductor() {
		return this.materialEarthingConductor;
	}

	public void setMaterialEarthingConductor(String materialEarthingConductor) {
		this.materialEarthingConductor = materialEarthingConductor;
	}

	public String getSizeEarthConductor() {
		return this.sizeEarthConductor;
	}

	public void setSizeEarthConductor(String sizeEarthConductor) {
		this.sizeEarthConductor = sizeEarthConductor;
	}

	public String getTypesJoints() {
		return this.typesJoints;
	}

	public void setTypesJoints(String typesJoints) {
		this.typesJoints = typesJoints;
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

	public PiDetailsMainProtectiveAddlocation getPiDetailsMainProtectiveAddlocation() {
		return this.piDetailsMainProtectiveAddlocation;
	}

	public void setPiDetailsMainProtectiveAddlocation(PiDetailsMainProtectiveAddlocation piDetailsMainProtectiveAddlocation) {
		this.piDetailsMainProtectiveAddlocation = piDetailsMainProtectiveAddlocation;
	}

}