package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ni_sign_contractor_table database table.
 * 
 */
@Entity
@Table(name="ni_sign_contractor_table")
@NamedQuery(name="NiSignContractorTable.findAll", query="SELECT n FROM NiSignContractorTable n")
public class NiSignContractorTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CONTRACTOR_ID")
	private int contractorId;

	@Column(name="ADDRESS_LINE1")
	private String addressLine1;

	@Column(name="ADDRESS_LINE2")
	private String addressLine2;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTACT_NO")
	private int contactNo;

	private String createdby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddate;

	@Column(name="EMAIL_ID")
	private String emailId;

	private String landmark;

	@Column(name="MANAGER_CONTACT_NO")
	private int managerContactNo;

	@Column(name="MANAGER_EMAIL_ID")
	private String managerEmailId;

	@Column(name="MANAGER_NAME")
	private String managerName;

	private String name;

	private int pincode;

	private String state;

	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateddt;

	public NiSignContractorTable() {
	}

	public int getContractorId() {
		return this.contractorId;
	}

	public void setContractorId(int contractorId) {
		this.contractorId = contractorId;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public int getManagerContactNo() {
		return this.managerContactNo;
	}

	public void setManagerContactNo(int managerContactNo) {
		this.managerContactNo = managerContactNo;
	}

	public String getManagerEmailId() {
		return this.managerEmailId;
	}

	public void setManagerEmailId(String managerEmailId) {
		this.managerEmailId = managerEmailId;
	}

	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPincode() {
		return this.pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdateddt() {
		return this.updateddt;
	}

	public void setUpdateddt(Date updateddt) {
		this.updateddt = updateddt;
	}

}