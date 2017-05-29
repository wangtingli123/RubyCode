package com.model;

public class PayStatus {
	private String payStatusId;
	private String payStatus;
	private Order order;
	public PayStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getPayStatusId() {
		return payStatusId;
	}
	public void setPayStatusId(String payStatusId) {
		this.payStatusId = payStatusId;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
}
