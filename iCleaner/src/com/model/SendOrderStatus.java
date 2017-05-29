package com.model;

public class SendOrderStatus {
	private String sendOrderId;
	private String sendOrderStatus;
	private PickUp pickUp;
	public PickUp getPickUp() {
		return pickUp;
	}
	public void setPickUp(PickUp pickUp) {
		this.pickUp = pickUp;
	}
	public String getSendOrderId() {
		return sendOrderId;
	}
	public void setSendOrderId(String sendOrderId) {
		this.sendOrderId = sendOrderId;
	}
	public String getSendOrderStatus() {
		return sendOrderStatus;
	}
	public void setSendOrderStatus(String sendOrderStatus) {
		this.sendOrderStatus = sendOrderStatus;
	}
	
}
