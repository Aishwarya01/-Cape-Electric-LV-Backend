package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "registered_license_table")
public class RegisteredLicense implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGISTERED_LICENSE_ID")
	private Integer registeredLicenseId;
	
	@Column(name = "NO_OF_LICENSE")
	private Integer noOfLicense;

	@Column(name = "LICENSE_AMOUNT")
	private Integer licenseCost;

	@Column(name = "PAYMENT_PHONE_NO")
	private String paymentPhoneNo;
	
	@Column(name = "PAYMENT_STATUS")
	private String paymentStatus;
	
	@Column(name = "PAYMENT_FAILED_DESCRIPTION")
	private String paymentFailedDescription;
	
	@Column(name = "ORDER_ID")
	private String orderId;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REGISTER_ID")
	private Register register;

	public Integer getRegisteredLicenseId() {
		return registeredLicenseId;
	}

	public void setRegisteredLicenseId(Integer registeredLicenseId) {
		this.registeredLicenseId = registeredLicenseId;
	}

	public Integer getNoOfLicense() {
		return noOfLicense;
	}

	public void setNoOfLicense(Integer noOfLicense) {
		this.noOfLicense = noOfLicense;
	}

	public Integer getLicenseCost() {
		return licenseCost;
	}

	public void setLicenseCost(Integer licenseCost) {
		this.licenseCost = licenseCost;
	}

	public String getPaymentPhoneNo() {
		return paymentPhoneNo;
	}

	public void setPaymentPhoneNo(String paymentPhoneNo) {
		this.paymentPhoneNo = paymentPhoneNo;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentFailedDescription() {
		return paymentFailedDescription;
	}

	public void setPaymentFailedDescription(String paymentFailedDescription) {
		this.paymentFailedDescription = paymentFailedDescription;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
