package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the company_table database table.
 * 
 */
@Entity
@Table(name = "company_table")
@NamedQueries(value = {
	@NamedQuery(name = "CompanyRepository.findByUserNameAndClientName", query = "select c.clientName from Company c where c.userName=:userName and c.clientName=:clientName"),
		@NamedQuery(name = "CompanyRepository.findByUserName", query = "select c from Company c where c.userName=:userName"),
		@NamedQuery(name = "CompanyRepository.findByClientName", query = "select c.clientName from Company c where c.clientName=:clientName")		
})

public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMPANY_ID")
	private Integer companyId;
	
	@Column(name = "COMPANY_CODE")						 
	private String companyCd;			  
 
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
	
	@JsonManagedReference
	@OneToMany(mappedBy="company", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Department> department;

	public Company() {
	}

	public Set<Department> getDepartment() {
		return department;
	}

	public void setDepartment(Set<Department> department) {
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

	public boolean getInActive() {
		return inActive;
	}

	public void setInActive(boolean inActive) {
		this.inActive = inActive;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

}