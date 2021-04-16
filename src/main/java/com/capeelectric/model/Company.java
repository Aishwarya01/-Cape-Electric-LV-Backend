package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the company_table database table.
 * 
 */
@Entity
@Table(name = "COMPANY_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "Company.findByClientName", query = "select c.clientName from Company c where c.clientName=?1"),
		@NamedQuery(name = "CompanyRepository.findByUserName", query = "select c from Company c")})

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
	private boolean inActive;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@OneToMany(mappedBy="company", fetch=FetchType.EAGER,targetEntity = Department.class)
	private List<Department> department;

	public Company() {
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
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

	public Boolean getInactive() {
		return inActive;
	}

	public void setInactive(Boolean inactive) {
		this.inActive = inactive;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}