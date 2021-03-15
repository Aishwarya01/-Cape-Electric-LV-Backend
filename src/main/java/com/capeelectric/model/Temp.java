package com.capeelectric.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Temp implements Serializable {           // Two screen same fields
	                                                 //	TODO add column name,tablename
	 
 	 
	private static final long serialVersionUID = 7313017434670662232L;
	@Id
	private Long clientIDKey;
	private Boolean inactiveFlag;
	private String clientName;
	private Timestamp createdDt;
	private Timestamp updatedDt;
	private String createdBy;
	private String updatedBy;
	private Boolean deletedFlg;

	public Long getClientIDKey() {
		return clientIDKey;
	}

	public void setClientIDKey(Long clientIDKey) {
		this.clientIDKey = clientIDKey;
	}

	public Boolean getInactiveFlag() {
		return inactiveFlag;
	}

	public void setInactiveFlag(Boolean inactiveFlag) {
		this.inactiveFlag = inactiveFlag;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public Timestamp getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
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

	public Boolean getDeletedFlg() {
		return deletedFlg;
	}

	public void setDeletedFlg(Boolean deletedFlg) {
		this.deletedFlg = deletedFlg;
	}

}
