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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the site_table database table.
 * 
 */
@Entity
@Table(name = "site_table")
@NamedQueries(value = {
		@NamedQuery(name = "SiteRepository.findByClientNameAndDepartmentName", query = "select s from Site s where s.clientName=:clientName and s.departmentName=:departmentName") })
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "SITE_ID")
	private Integer siteId;

	@Column(name = "SITE_CODE")
	private String siteCd;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "SITE")
	private String site;

	@Column(name = "ADDRESSLINE_1")
	private String addressLine_1;

	@Column(name = "ADDRESSLINE_2")
	private String addressLine_2;

	@Column(name = "LAND_MARK")
	private String landMark;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "ZIP_CODE")
	private String zipCode;

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
	@JoinColumn(name = "DEPARTMENT_ID")
	private Department department;

	
	@OneToMany(mappedBy="Site",fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	private Set<SitePersons> sitePersons;
	
	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
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

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getAddressLine_1() {
		return addressLine_1;
	}

	public void setAddressLine_1(String addressLine_1) {
		this.addressLine_1 = addressLine_1;
	}

	public String getAddressLine_2() {
		return addressLine_2;
	}

	public void setAddressLine_2(String addressLine_2) {
		this.addressLine_2 = addressLine_2;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getSiteCd() {
		return siteCd;
	}

	public void setSiteCd(String siteCd) {
		this.siteCd = siteCd;
	}

	@JsonManagedReference
	public Set<SitePersons> getSitePersons() {
		return sitePersons;
	}

	public void setSitePersons(Set<SitePersons> sitePersons) {
		this.sitePersons = sitePersons;
	}

}