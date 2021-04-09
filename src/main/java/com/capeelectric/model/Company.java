package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * The persistent class for the company_table database table.
 * 
 */
@Entity
@Table(name = "COMPANY_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "CompanyRepository.findByClientName", query = "select c.clientName from Company c where c.clientName=:clientName"),
		@NamedQuery(name = "CompanyRepository.findByUserName", query = "select c from Company c") })
 
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMPANY_ID")
	private Integer companyId;
	
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "IN_ACTIVE")
	private Byte inactive;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;

	public Company() {
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Byte getInactive() {
		return inactive;
	}

	public void setInactive(Byte inactive) {
		this.inactive = inactive;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}