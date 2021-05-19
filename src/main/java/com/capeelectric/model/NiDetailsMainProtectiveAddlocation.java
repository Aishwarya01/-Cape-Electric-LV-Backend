package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ni_details_main_protective_addlocation database table.
 * 
 */
@Entity
@Table(name="ni_details_main_protective_addlocation")
@NamedQuery(name="NiDetailsMainProtectiveAddlocation.findAll", query="SELECT n FROM NiDetailsMainProtectiveAddlocation n")
public class NiDetailsMainProtectiveAddlocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOCATION_ID")
	private int locationId;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="JOINT_NO")
	private String jointNo;

	@Column(name="JOINT_RESISTANCE")
	private String jointResistance;

	private String location;

	@Column(name="MAIN_PROTECTIVE_KEY")
	private int mainProtectiveKey;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	//bi-directional one-to-one association to NiDetailsMainProtective
	@OneToOne
	@JoinColumn(name="LOCATION_ID")
	private NiDetailsMainProtective niDetailsMainProtective;

	public NiDetailsMainProtectiveAddlocation() {
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

	public String getJointNo() {
		return this.jointNo;
	}

	public void setJointNo(String jointNo) {
		this.jointNo = jointNo;
	}

	public String getJointResistance() {
		return this.jointResistance;
	}

	public void setJointResistance(String jointResistance) {
		this.jointResistance = jointResistance;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMainProtectiveKey() {
		return this.mainProtectiveKey;
	}

	public void setMainProtectiveKey(int mainProtectiveKey) {
		this.mainProtectiveKey = mainProtectiveKey;
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

	public NiDetailsMainProtective getNiDetailsMainProtective() {
		return this.niDetailsMainProtective;
	}

	public void setNiDetailsMainProtective(NiDetailsMainProtective niDetailsMainProtective) {
		this.niDetailsMainProtective = niDetailsMainProtective;
	}

}