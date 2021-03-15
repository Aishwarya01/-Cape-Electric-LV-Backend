package com.capeelectric.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class DepartmentDetails implements Serializable {      //TODO add column name,tablename
	protected static final long serialVersionUID=1L;
	@Id
	private Long departmentIdkey;
	private String departmentName;
	private Timestamp createdDt;
	private String createdBy;
	private Timestamp updateDt;
	private String updateBy;
	private Long clientIDKey;
	private Boolean inactiveFlg;
	private Boolean deletedFlg;

	public Long getDepartmentIdkey() {
		return departmentIdkey;
	}

	public void setDepartmentIdkey(Long departmentIdkey) {
		this.departmentIdkey = departmentIdkey;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Timestamp updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Long getClientIDKey() {
		return clientIDKey;
	}

	public void setClientIDKey(Long clientIDKey) {
		this.clientIDKey = clientIDKey;
	}

	public Boolean getInactiveFlg() {
		return inactiveFlg;
	}

	public void setInactiveFlg(Boolean inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public Boolean getDeletedFlg() {
		return deletedFlg;
	}

	public void setDeletedFlg(Boolean deletedFlg) {
		this.deletedFlg = deletedFlg;
	}

}
