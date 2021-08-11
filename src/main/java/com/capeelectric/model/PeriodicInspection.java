package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "periodic_inspection_table")
@NamedQueries(value = {
		@NamedQuery(name = "InspectionRepository.findBySiteId", query = "select i.siteId from PeriodicInspection i where i.siteId=:siteId"),
		@NamedQuery(name = "InspectionRepository.findByUserNameAndSiteId", query = "select i from PeriodicInspection i where i.userName=:userName and i.siteId=:siteId"), })
public class PeriodicInspection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERIODIC_INSPECTION_ID")
	private Integer periodicInspectionId;

	@Column(name = "SITE_ID")
	private Integer siteId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "periodicInspection", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<IpaoInspection> ipaoInspection;

	public Integer getPeriodicInspectionId() {
		return periodicInspectionId;
	}

	public void setPeriodicInspectionId(Integer periodicInspectionId) {
		this.periodicInspectionId = periodicInspectionId;
	}

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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<IpaoInspection> getIpaoInspection() {
		return ipaoInspection;
	}

	public void setIpaoInspection(List<IpaoInspection> ipaoInspection) {
		this.ipaoInspection = ipaoInspection;
	}
 
}
