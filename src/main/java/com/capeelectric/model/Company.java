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
@NamedQueries(value = {
		@NamedQuery(name = "Company.findByClientName",
				query = "select c.clientName from Company c where c.clientName=?1")
})

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="COMPANY_ID")
	private Integer companyId;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	private String createdBy;

	private String updatedBy;

	private LocalDateTime createdDate;

	private Byte inactive;

	private LocalDateTime updatedDate;

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