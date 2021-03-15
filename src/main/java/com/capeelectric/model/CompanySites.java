package com.capeelectric.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class CompanySites implements Serializable{          //TODO add column name,tablename
	 
	private static final long serialVersionUID = 6814116756223280537L;
	@Id
	private Long siteIdKey;
	private String siteName;
	private Long clientIDKey;
	private Long departmentIdkey;
	private String personIncharge;
	private String e_mail;
	private String addressLine_1;
	private String addressLine_2;
	private String landMark;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String phoneNumber;
	private Boolean inactiveFlg;
	private Timestamp createdDt;
	private String createdBy;
	private Timestamp updateDt;
	private String updateBy;
	private Boolean deletedFlg;

	public Long getSiteIdKey() {
		return siteIdKey;
	}

	public void setSiteIdKey(Long siteIdKey) {
		this.siteIdKey = siteIdKey;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Long getClientIDKey() {
		return clientIDKey;
	}

	public void setClientIDKey(Long clientIDKey) {
		this.clientIDKey = clientIDKey;
	}

	public Long getDepartmentIdkey() {
		return departmentIdkey;
	}

	public void setDepartmentIdkey(Long departmentIdkey) {
		this.departmentIdkey = departmentIdkey;
	}

	public String getPersonIncharge() {
		return personIncharge;
	}

	public void setPersonIncharge(String personIncharge) {
		this.personIncharge = personIncharge;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getInactiveFlg() {
		return inactiveFlg;
	}

	public void setInactiveFlg(Boolean inactiveFlg) {
		this.inactiveFlg = inactiveFlg;
	}

	public Timestamp getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Timestamp updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Boolean getDeletedFlg() {
		return deletedFlg;
	}

	public void setDeletedFlg(Boolean deletedFlg) {
		this.deletedFlg = deletedFlg;
	}

}
