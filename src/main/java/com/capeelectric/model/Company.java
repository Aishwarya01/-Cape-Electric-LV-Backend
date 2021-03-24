package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * The persistent class for the company_table database table.
 * 
 */
@Entity
@Table(name = "company_table")
@NamedQuery(name = "CompanyTable.findAll", query = "SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMPANY_TABLE_COMPANYID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMPANY_TABLE_COMPANYID_GENERATOR")
	@Column(name="COMPANY_ID")
	private Integer companyId;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	private String createdby;

	private String updatedby;

	private LocalDateTime createDate;

	private Byte inactive;

	private LocalDateTime updateDate;

	public Company() {
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Byte getInactive() {
		return inactive;
	}

	public void setInactive(Byte inactive) {
		this.inactive = inactive;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	 

}