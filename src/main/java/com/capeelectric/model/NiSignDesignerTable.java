package com.capeelectric.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ni_sign_designer_table database table.
 * 
 */
@Entity
@Table(name="ni_sign_designer_table")
@NamedQuery(name="NiSignDesignerTable.findAll", query="SELECT n FROM NiSignDesignerTable n")
public class NiSignDesignerTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DESIGNER_ID")
	private int designerId;

	@Column(name="ADDRESS_LINE1")
	private String addressLine1;

	@Column(name="ADDRESS_LINE2")
	private String addressLine2;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTACT_NO")
	private int contactNo;

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

	public NiSignDesignerTable() {
	}

	public int getDesignerId() {
		return this.designerId;
	}

	public void setDesignerId(int designerId) {
		this.designerId = designerId;
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

}