package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licence_table")
public class LicenseTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LICENCE_ID")
	private Integer licenceId;
	
	@Column(name = "NO_OF_LV_LICENCE")
	private String noOfLVLicence;
	
	@Column(name = "STATUS")
	private String status;

	public Integer getLicenceId() {
		return licenceId;
	}

	public void setLicenceId(Integer licenceId) {
		this.licenceId = licenceId;
	}

	public String getNoOfLVLicence() {
		return noOfLVLicence;
	}

	public void setNoOfLVLicence(String noOfLVLicence) {
		this.noOfLVLicence = noOfLVLicence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
