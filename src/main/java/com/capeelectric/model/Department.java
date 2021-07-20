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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the department_table database table.
 * 
 */
@Entity
@Table(name = "DEPARTMENT_TABLE")
@NamedQueries(value = {
		@NamedQuery(name = "DepartmentRepository.findByClientName", query = "select  d from Department d where d.clientName=:clientName"),
		@NamedQuery(name = "DepartmentRepository.findByUserNameAndClientName", query = "select  d from Department d where d.userName=:userName and d.clientName=:clientName"),
		@NamedQuery(name = "Department.findByDepartmentName", query = "select d.departmentName,d.departmentId from Department d where d.departmentName=:departmentName"),
		@NamedQuery(name = "DepartmentRepository.findByClientNameAndDepartmentName", query = "select d.departmentName from Department d where d.clientName=:clientName and d.departmentName=:departmentName"), })
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEPARTMENT_ID")
	private Integer departmentId;
	
	@Column(name = "DEPARTMENT_CODE")
	private String departmentCd;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "CLIENT_NAME")
	private String clientName;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private Company company;
	
	@JsonManagedReference
	@OneToMany(mappedBy="department",fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private Set<Site> site;

	public Set<Site> getSite() {
		return site;
	}

	public void setSite(Set<Site> site) {
		this.site = site;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
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

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
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

	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getDepartmentCd() {
		return departmentCd;
	}

	public void setDepartmentCd(String departmentCd) {
		this.departmentCd = departmentCd;
	}

}