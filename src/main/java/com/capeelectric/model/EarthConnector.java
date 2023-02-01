package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author CAPE-SOFTWARE
 *
 */
@Entity
@Table(name = "earth_connector_table")
public class EarthConnector implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = " EARTH_CONNECTORID")
	private Integer earthconnectorid;

	@Column(name = " EARTH_CABLE_CONNECTORID")
	private Integer earthCableConnectorid;

	@Column(name = " TYPE_OF_ELECTRODE")
	private Integer typeOfElectrode;

	@Column(name = " MATERIAL_ELECTRODE")
	private Integer materialElectrode;

	@Column(name = " SIZE_ELECTRODE")
	private Integer sizeElectrode;

	@Column(name = " DEPTH_ELECTRODE")
	private Integer depthElectrode;

	@Column(name = " FILE_NAME")
	private String fileName;

	@Column(name = "  EARTH_ELECTRODE_RESISTANCE")
	private String earthElectrodeResistance;

	@Column(name = "COMBINED_RESISTANCE")
	private String combinedResistance;

	@Column(name = "NODE_ID")
	private String nodeId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "FLAG")
	private String flag;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Integer getEarthconnectorid() {
		return earthconnectorid;
	}

	public void setEarthconnectorid(Integer earthconnectorid) {
		this.earthconnectorid = earthconnectorid;
	}

	public Integer getEarthCableConnectorid() {
		return earthCableConnectorid;
	}

	public void setEarthCableConnectorid(Integer earthCableConnectorid) {
		this.earthCableConnectorid = earthCableConnectorid;
	}

	public Integer getTypeOfElectrode() {
		return typeOfElectrode;
	}

	public void setTypeOfElectrode(Integer typeOfElectrode) {
		this.typeOfElectrode = typeOfElectrode;
	}

	public Integer getMaterialElectrode() {
		return materialElectrode;
	}

	public void setMaterialElectrode(Integer materialElectrode) {
		this.materialElectrode = materialElectrode;
	}

	public Integer getSizeElectrode() {
		return sizeElectrode;
	}

	public void setSizeElectrode(Integer sizeElectrode) {
		this.sizeElectrode = sizeElectrode;
	}

	public Integer getDepthElectrode() {
		return depthElectrode;
	}

	public void setDepthElectrode(Integer depthElectrode) {
		this.depthElectrode = depthElectrode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEarthElectrodeResistance() {
		return earthElectrodeResistance;
	}

	public void setEarthElectrodeResistance(String earthElectrodeResistance) {
		this.earthElectrodeResistance = earthElectrodeResistance;
	}

	public String getCombinedResistance() {
		return combinedResistance;
	}

	public void setCombinedResistance(String combinedResistance) {
		this.combinedResistance = combinedResistance;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
