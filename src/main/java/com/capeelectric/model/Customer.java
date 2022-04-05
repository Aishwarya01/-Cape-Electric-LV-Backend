package com.capeelectric.model;

public class Customer {

	private String customerName;
	private String email;
	private String phoneNumber;
	private Integer amount;
	private Integer noofLicense;
	private Integer inspectorRegisterId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getInspectorRegisterId() {
		return inspectorRegisterId;
	}

	public void setInspectorRegisterId(Integer inspectorRegisterId) {
		this.inspectorRegisterId = inspectorRegisterId;
	}

	public Integer getNoofLicense() {
		return noofLicense;
	}

	public void setNoofLicense(Integer noofLicense) {
		this.noofLicense = noofLicense;
	}
}