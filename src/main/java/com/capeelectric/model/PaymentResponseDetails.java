package com.capeelectric.model;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public class PaymentResponseDetails {

	private String orderId;

	private Integer inspectorRegisterdId;

	private String descriptionOffailedPayment;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getInspectorRegisterdId() {
		return inspectorRegisterdId;
	}

	public void setInspectorRegisterdId(Integer inspectorRegisterdId) {
		this.inspectorRegisterdId = inspectorRegisterdId;
	}

	public String getDescriptionOffailedPayment() {
		return descriptionOffailedPayment;
	}

	public void setDescriptionOffailedPayment(String descriptionOffailedPayment) {
		this.descriptionOffailedPayment = descriptionOffailedPayment;
	}

}
