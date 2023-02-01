/**
 * 
 */
package com.capeelectric.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "EquipotentialBonding")
public class EquipotentialBonding {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EQUIPBOND_ID")
	private Integer equipBondID;

	@Column(name = "NODE_ID")
	private String nodeId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "REFERENCE_NAME")
	private String referenceName;

	@Column(name = "RATING")
	private Integer rating;

	@Column(name = "SIZE_ONE")
	private String sizeOne;

	@Column(name = "SIZE_TWO")
	private String sizeTwo;

	@Column(name = "MAKE")
	private String make;

	@Column(name = "TYPE_FLAT_OR_BASE")
	private String typeFlatOrBase;

	@Column(name = "MATERIAL")
	private String material;

	@Column(name = "CONDUCTOR_LENGTH")
	private String conductorLength;

	@Column(name = "CONDUCTOR_RESISTANCE")
	private String conductorResistance;

	@Column(name = "MAX_ALLOWABLE_RESISTANCE")
	private String maxAllowableResistance;
	
	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getEquipBondID() {
		return equipBondID;
	}

	public void setEquipBondID(Integer equipBondID) {
		this.equipBondID = equipBondID;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getSizeOne() {
		return sizeOne;
	}

	public void setSizeOne(String sizeOne) {
		this.sizeOne = sizeOne;
	}

	public String getSizeTwo() {
		return sizeTwo;
	}

	public void setSizeTwo(String sizeTwo) {
		this.sizeTwo = sizeTwo;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getTypeFlatOrBase() {
		return typeFlatOrBase;
	}

	public void setTypeFlatOrBase(String typeFlatOrBase) {
		this.typeFlatOrBase = typeFlatOrBase;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getConductorLength() {
		return conductorLength;
	}

	public void setConductorLength(String conductorLength) {
		this.conductorLength = conductorLength;
	}

	public String getConductorResistance() {
		return conductorResistance;
	}

	public void setConductorResistance(String conductorResistance) {
		this.conductorResistance = conductorResistance;
	}

	public String getMaxAllowableResistance() {
		return maxAllowableResistance;
	}

	public void setMaxAllowableResistance(String maxAllowableResistance) {
		this.maxAllowableResistance = maxAllowableResistance;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
